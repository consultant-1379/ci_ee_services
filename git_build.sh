#!/bin/bash

if [ "$2" == "" ]; then
    	echo usage: $0 \<Branch\> \<RState\>
    	exit -1
else
	versionProperties=install/version.properties
	theDate=\#$(date +"%c")
	module=$1
	branch=$2
	workspace=$3
	userId=$4
    	deliver=$5
    	reason=$6
    	CT=/usr/atria/bin/cleartool
	warFile=${workspace}/ee-services-core/services-main/target/EniqEventsServices.war
fi

function getReason {
        if [ -n "$reason" ]; then
                reason=`echo $reason | sed 's/$\ /x/'`
                reason=`echo JIRA:::$reason | sed s/" "/,JIRA:::/g`
        else
                reason="CI-DEV"
        fi
}

function getSprint {
        sprint=`cat $workspace/build.cfg | grep $module | grep $branch | awk -F " " '{print $5}'`
}

function getProductNumber {
        product=`cat $workspace/build.cfg | grep $module | grep $branch | awk -F " " '{print $3}'`
}


function setRstate {

        revision=`cat $workspace/build.cfg | grep $module | grep $branch | awk -F " " '{print $4}'`

       	if git tag | grep $product-$revision; then
	        rstate=`git tag | grep $revision | tail -1 | sed s/.*-// | perl -nle 'sub nxt{$_=shift;$l=length$_;sprintf"%0${l}d",++$_}print $1.nxt($2) if/^(.*?)(\d+$)/';`
        else
                ammendment_level=01
                rstate=$revision$ammendment_level
        fi
	echo "Building R-State:$rstate"

}

getSprint
getProductNumber
setRstate
git checkout $branch
git pull origin $branch

#add maven command here
mvn clean package -Dmaven.test.skip=true -P noPMD -U -s /proj/jkadm100/apache-maven-3.0.4_oge/conf/setting_for_eniq_service.xml 

rsp=$?

if [ $rsp == 0 ]; then
  touch $versionProperties
  echo $theDate >> $versionProperties
  echo module.name=eventsservices >> $versionProperties
  echo module.version=$rstate >> $versionProperties
  echo build.tag=b999 >> $versionProperties
  echo author=jkadm100 >> $versionProperties
  echo module.build=999 >> $versionProperties
  echo product.number=$product >> $versionProperties
  echo product.label=$tag_product-$rstate >> $versionProperties

  cp ${warFile} ${workspace}/install
  zip -r eventsservices_${rstate}.zip install/*
  echo "Copying eventsservices_$rstate.zip to /home/jkadm100/eniq_events_releases"
  cp eventsservices_$rstate.zip /home/jkadm100/eniq_events_releases
  git tag $product-$rstate
  git push --tag origin $branch

  if [ "${deliver}" == "D" ] ; then
    echo "Running delivering..."
    getReason
    echo "Zip file: /home/jkadm100/eniq_events_releases/eventsservices_$rstate.zip"
    echo "Sprint: $sprint"
    echo "UserId: $userId"
    echo "Product Number: $product"
    echo "Running command: /vobs/dm_eniq/tools/scripts/deliver_eniq -auto events $sprint $reason N $userId $product NONE /home/jkadm100/eniq_events_releases/eventsservices_$rstate.zip"
    $CT setview -exec "cd /vobs/dm_eniq/tools/scripts;./deliver_eniq -auto events $sprint $reason N $userId $product NONE /home/jkadm100/eniq_events_releases/eventsservices_$rstate.zip" deliver_ui
  fi
fi

exit $rsp
