package com.ericsson.eniq.events.server.test.stubs;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * Injecting URIInfo for IntegrationTests that don't have EJB container available 
 * @author eemecoy
 *
 */
public class DummyUriInfoImpl implements UriInfo {

    private final MultivaluedMap<String, String> urlParams;

    private String restPath;

    private URI baseUri;

    private URI fullURI;

    public DummyUriInfoImpl(final MultivaluedMap<String, String> map, final String uri, final String path)
            throws URISyntaxException {
        urlParams = map;
        restPath = path;
        if (uri != null) {
            baseUri = new URI(uri);
            fullURI = new URI(uri + "/" + path);
        }
    }

    public DummyUriInfoImpl(final MultivaluedMap<String, String> map) throws URISyntaxException {
        urlParams = map;
        baseUri = new URI("");
    }

    public static void setUriInfo(final MultivaluedMap<String, String> map, final Object resource)
            throws URISyntaxException {
        setUriInfo(map, resource, "baseURI", "somePath");
    }

    public static void setUriInfoForMss(final MultivaluedMap<String, String> map, final Object resource)
            throws URISyntaxException {
        setUriInfoForMss(map, resource, "baseURI", "somePath");
    }

    public static void setUriInfo(final MultivaluedMap<String, String> map, final Object resource,
            final String baseURI, final String path) throws URISyntaxException {
        final Field[] fields = resource.getClass().getSuperclass().getDeclaredFields();
        for (final Field f : fields) {
            final Context c = f.getAnnotation(Context.class);
            if (c != null) {
                try {
                    f.setAccessible(true);
                    f.set(resource, new DummyUriInfoImpl(map, baseURI, path));
                    f.setAccessible(false);
                    break;
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setUriInfoMss(final MultivaluedMap<String, String> map, final Object resource)
            throws URISyntaxException {
        setUriInfoForMss(map, resource, "baseURI", "somePath");
    }

    public static void setUriInfoForMss(final MultivaluedMap<String, String> map, final Object resource,
            final String baseURI, final String path) throws URISyntaxException {
        // ezhibhe, MSS Resources' super class is MssBaseResource, 
        // whose super class is BaseResource
        final Class<?> baseResourceClass = resource.getClass().getSuperclass().getSuperclass();
        final Field[] fields = baseResourceClass.getDeclaredFields();
        for (final Field f : fields) {
            final Context c = f.getAnnotation(Context.class);
            if (c != null) {
                try {
                    f.setAccessible(true);
                    f.set(resource, new DummyUriInfoImpl(map, baseURI, path));
                    f.setAccessible(false);
                    break;
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getPath() {
        return restPath;
    }

    @Override
    public String getPath(final boolean b) {
        return null;
    }

    @Override
    public List<PathSegment> getPathSegments() {
        return null;
    }

    @Override
    public List<PathSegment> getPathSegments(final boolean b) {
        return null;
    }

    @Override
    public URI getRequestUri() {
        return baseUri;
    }

    @Override
    public UriBuilder getRequestUriBuilder() {
        return null;
    }

    @Override
    public URI getAbsolutePath() {
        return fullURI;
    }

    @Override
    public UriBuilder getAbsolutePathBuilder() {
        return null;
    }

    @Override
    public URI getBaseUri() {
        return baseUri;
    }

    @Override
    public UriBuilder getBaseUriBuilder() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters() {
        return urlParams;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters(final boolean b) {
        return urlParams;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters() {
        return urlParams;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters(final boolean b) {
        return urlParams;
    }

    @Override
    public List<String> getMatchedURIs() {
        return null;
    }

    @Override
    public List<String> getMatchedURIs(final boolean b) {
        return null;
    }

    @Override
    public List<Object> getMatchedResources() {
        return null;
    }
}
