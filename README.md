# java-jwt-tools

This library is used to get access tokens(JWT), decode JWTs, and get user's Scopes, Groups, Roles, and Permissions information.
We use https://jitpack.io/#ModaOperandi/java-jwt-tools to publish the artifact for consumer to use

To install the library, please add dependencies to your build file:

	repositories { 
		...
		maven {
        		url "https://jitpack.io"
		}
	}

	dependencies {
        	implementation 'com.github.ModaOperandi:java-jwt-tools:version'
	}


To use it, just need to import one package to your controllers:

	import com.modaoperandi.jwt.java.DecodedToken;
	
Here is an example:

	@Autowired
	private HttpServletRequest request;
  
  		// Include codes below in your methods:
        
		DecodedToken decodedToken = new DecodedToken(request, namespace, jwkUrl);
		String authToken = decodedToken.getToken();
		String scopes = decodedToken.getScopes();
		String[] groups = decodedToken.getGroups();
		String[] roles = decodedToken.getRoles();
		String[] permissions = decodedToken.getPermissions();
		
		decodedToken.printUserInfo();     // print out Scopes, Groups, Roles, and Permissions
	    decodedToken.printToken();        // print out authToken
		decodedToken.printScopes();       // print out Scopes
		decodedToken.printGroups();       // print out Groups
		decodedToken.printRoles();        // print out Roles
		decodedToken.printPermissions();  // print out Permissions

