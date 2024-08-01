export const oktaConfig = {
    clientId: '0oaio110jjXqg6hc45d7',
    issuer: 'https://dev-81791657.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}