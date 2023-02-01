/**
 * Available variables
 * user - the current user
 * realm - the current realm
 * token - the current token
 * userSession - the current userSession
 * keycloakSession - the current keycloakSession
 */

// Insert code here

var username = user.getUserName();

token.setOtherClaims("example", username);