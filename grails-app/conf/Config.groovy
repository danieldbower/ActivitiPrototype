// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

grails.plugins.dynamicController.mixins = [
	'com.burtbeckwith.grails.plugins.appinfo.IndexControllerMixin':
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.Log4jControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.SpringControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.MemoryControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.PropertiesControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.ScopesControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'com.burtbeckwith.grails.plugins.appinfo.ThreadsControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController',
  
	'app.info.custom.example.MyConfigControllerMixin' :
	   'com.burtbeckwith.appinfo_test.AdminManageController'
 ]

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'myapp.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'myapp.UserRole'
grails.plugins.springsecurity.authority.className = 'myapp.Role'

// Added by the Grails Activiti plugin:
activiti {
	processEngineName = "activiti-engine-default"
	  databaseType = "h2"
	  deploymentName = appName
	  deploymentResources = ["file:./grails-app/conf/**/*.bpmn*.xml",
							 "file:./grails-app/conf/**/*.png",
							 "file:./src/taskforms/**/*.form"]
	  jobExecutorActivate = false
	  mailServerHost = "smtp.yourserver.com"
	  mailServerPort = "25"
	  mailServerUsername = ""
	  mailServerPassword = ""
	  mailServerDefaultFrom = "username@yourserver.com"
	  history = "audit" // "none", "activity", "audit" or "full"
	  sessionUsernameKey = "username"
	  useFormKey = true
}

environments {
	development {
		activiti {
			  processEngineName = "activiti-engine-dev"
			  databaseSchemaUpdate = true // true, false or "create-drop"
		}
	}
	test {
		activiti {
			  processEngineName = "activiti-engine-test"
			  databaseSchemaUpdate = true
		  mailServerPort = "5025"
		}
	}
	production {
		activiti {
			  processEngineName = "activiti-engine-prod"
			  databaseSchemaUpdate = false
			  jobExecutorActivate = true
		}
	}
}

