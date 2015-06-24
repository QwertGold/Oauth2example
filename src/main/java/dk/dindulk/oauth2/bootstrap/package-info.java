/**
 *
 * <p>
 * Classes for bootstrapping Spring. Spring-web comes with a ServletContainerInitializer which is used as an entrypoint
 * for the application, this in turn delegate to any class that implement Springs WebApplicationInitializer such as the
 * SecurityInitializer and SpringMvcInitializer.
 * </p>
 * Oauth flow:
 * <ol>
 * <li>
 * Oauth client Posts to /oauth/authorize?client_id=[client_id]&amp;scope=[scope]&amp;response_type=[token|code],
 * this URL requires authentication, and must be protected (typically by form login). The request therefore gets redirected
 * to /login, where the user enters the credentials.
 * </li>
 * <li> When the login form is submitted, the original request is completed showing the "accept grant" page to the end-user. Inside
 * Spring this is a internal forward to /oauth/confirm_access which may be overridden in order to style the grant page.
 * </li>
 * <li> If the grant is approved the browser is redirected to the registered redirectURL, either with a code or a access_token,
 * based on the response_type .
 * </li><li>
 * If the redirect contains an access_token, this will be sent as a fragment and can be retrieved using JavaScript
 * </li><li>
 * If the redirect contains a code (as query parameter), the oauth-client must extract this and then call /oauth/token to get the code
 * exchanged to a access_token. /oauth/token is protected by basic authentication in order to authenticate the client
 * </li>
 *</ol>
 *
 *
 * @author Klaus Groenbaek
 * Created 24/06/15.
 */
package dk.dindulk.oauth2.bootstrap;