
PLEASE CONTACT EEIKNAR (Kamal Nazar) with any PMD problems/issues

SourceForge PMD issue tracking page:
http://pmd.sourceforge.net/project-info.html

*********************************************************************
              I M P O R T A N T   I N F O R M A T I O N
*********************************************************************

The ONLY supported version of PMD within OSSRC is PMD version 2.3 
DO NOT download and use ANY OTHER version apart from 2.3
=====================================================================
			L A T E S T   N E W S
=====================================================================

10.08.05

PMD-2.3 no longer available at SourceForge since version 3.x released

PMD 2.3 zip archive can be found in:
\RanosDoc\doc_store\P4\PMDRules\PMD Archive


=====================================================================
21.07.05

Problems with JBuilder2005 and Ant 1.6/1.6.1

Unable to instantiate specified input handler 
class com.borland.jbuilder.build.antinput.AntInputHandler: 

Please see:

http://qc.borland.com/wc/qcmain.aspx?d=9667

Workaround:

run PMD related ant build.xml externally - i.e. at the DOS prompt
=====================================================================
FALSE POSITIVE - unused private method


Under some (unknown) circumstances PMD 2.3 gives false positives
on the UnusedPrivateMethod rule - this bug is being tracked at SourceForge
but there is currently no solution

SourceForge request ID = 1114754

=====================================================================
FALSE POSITIVE - Constructor calls overridable method


Under some (partially known) circumstances PMD 2.3 gives false positives
on the ConstructorCallsOverridableMethod rule - this bug is being tracked at SourceForge
but there is currently no solution

SourceForge request ID = 1200794 



One situation where this problem is known to occur is as follows:


The problem occurs when inside the constructor for a 
given class (say Class X) - which also has access to an 
instance of class Y - Class X and Class Y both have 
methods with identical signatures (which are overridable).
But X and X are not related by inheritance



public class CarPark {

  public CarPark(Car car){
    
    car.parkMe();            	// ****** no PMD error
    
    ((HugeCar)car).parkMe(); 	// ****** downcast/upcast
                          	// -> PMD reports false +ve
    this.parkMe();           	// ****** PMD error reported correctly 
                          	//(CarPark has a subtype)
  }

  public void parkMe(){}

}//end class CarPark


public class Car {

  public void parkMe(){}

} //end class Car


public class HugeCar extends Car {

  public void parkMe(){}

} //end class HugeCar


// now invoke the CarPark constructor

CarPark carPark = new CarPark(new HugeCar());


The problem occurs when an attempt is made to 
downcast/upcast - PMD doesn't recognise that
the invocation is on a method in another class 
and reports a false positive.


=====================================================================

