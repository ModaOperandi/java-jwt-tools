# java-jwt-tools

This is a library currently used to get access token(JWT) from your front end, decode it, and get user's Scopes, Groups, Roles, and Permissions information.

To install the library, please add dependencies to your build file:

	repositories { 
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        compile 'com.github.ModaOperandi:mo-jwt-java:v0.1.0'
    }


To use JWTDecoder and JWTGetter in your classes, please add these packages:

	import com.modaoperandi.jwt.java.JWTGetter;
	import com.modaoperandi.jwt.java.JWTDecoder;

Here is an example codes to add in your controllers:

	@Autowired
	private HttpServletRequest request;
  
    JWTGetter jwtGetter = new JWTGetter();
	String accessToken = jwtGetter.getAccessToken(request);
	JWTDecoder jwtDecoder = new JWTDecoder();
	jwtDecoder.getGroups(accessToken);
	jwtDecoder.getRoles(accessToken);
	jwtDecoder.getPermissions(accessToken);
    jwtDecoder.getScopes(accessToken);
