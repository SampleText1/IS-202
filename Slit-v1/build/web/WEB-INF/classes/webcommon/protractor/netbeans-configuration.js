/* 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2014 Sun Microsystems, Inc.
 */

// Reference Configuration File
//
// This file shows all of the configuration options that may be passed
// to Protractor.

var path = require('path');
var SPECS = process.env.SPECS.split(', ');
var JASMINE_NB_REPORTER = process.env.JASMINE_NB_REPORTER;
var USER_CONFIG_FILE = process.env.USER_CONFIGURATION_FILE;
var USER_CONFIG = require(USER_CONFIG_FILE).config;

var SELENIUM_SERVER_JAR = null;
if(USER_CONFIG.seleniumServerJar) {
    SELENIUM_SERVER_JAR = path.join(path.dirname(USER_CONFIG_FILE), USER_CONFIG.seleniumServerJar);
}

var CHROME_DRIVER = null;
if(USER_CONFIG.chromeDriver) {
    CHROME_DRIVER = path.join(path.dirname(USER_CONFIG_FILE), USER_CONFIG.chromeDriver);
}

exports.config = {
  // ---------------------------------------------------------------------------
  // ----- How to connect to Browser Drivers -----------------------------------
  // ---------------------------------------------------------------------------
  //
  // Protractor needs to know how to connect to Drivers for the browsers
  // it is testing on. This is usually done through a Selenium Server.
  // There are four options - specify one of the following:
  //
  // 1. seleniumServerJar - to start a standalone Selenium Server locally.
  // 2. seleniumAddress - to connect to a Selenium Server which is already
  //    running.
  // 3. sauceUser/sauceKey - to use remote Selenium Servers via Sauce Labs.
  // 4. directConnect - to connect directly to the browser Drivers.
  //    This option is only available for Firefox and Chrome.

  // ---- 1. To start a standalone Selenium Server locally ---------------------
  // The location of the standalone Selenium Server jar file, relative
  // to the location of this config. If no other method of starting Selenium
  // Server is found, this will default to
  // node_modules/protractor/selenium/selenium-server...
  seleniumServerJar: SELENIUM_SERVER_JAR,
  // The port to start the Selenium Server on, or null if the server should
  // find its own unused port. Ignored if seleniumServerJar is null.
  seleniumPort: USER_CONFIG.seleniumPort || null,
  // Additional command line options to pass to selenium. For example,
  // if you need to change the browser timeout, use
  // seleniumArgs: ['-browserTimeout=60']
  // Ignored if seleniumServerJar is null.
  seleniumArgs: USER_CONFIG.seleniumArgs || [],
  // ChromeDriver location is used to help find the chromedriver binary.
  // This will be passed to the Selenium jar as the system property
  // webdriver.chrome.driver. If null, Selenium will
  // attempt to find ChromeDriver using PATH.
  chromeDriver: CHROME_DRIVER,

  // ---- 2. To connect to a Selenium Server which is already running ----------
  // The address of a running Selenium Server. If specified, Protractor will
  // connect to an already running instance of Selenium. This usually looks like
 seleniumAddress: USER_CONFIG.seleniumAddress || null,

  // ---- 3. To use remote browsers via Sauce Labs -----------------------------
  // If sauceUser and sauceKey are specified, seleniumServerJar will be ignored.
  // The tests will be run remotely using Sauce Labs.
  sauceUser: USER_CONFIG.sauceUser || null,
  sauceKey: USER_CONFIG.sauceKey || null,
  // Use sauceSeleniumAddress if you need to customize the URL Protractor
  // uses to connect to sauce labs (for example, if you are tunneling selenium
  // traffic through a sauce connect tunnel). Default is
  // ondemand.saucelabs.com:80/wd/hub
  sauceSeleniumAddress: USER_CONFIG.sauceSeleniumAddress || null,

  // ---- 4. To connect directly to Drivers ------------------------------------
  // Boolean. If true, Protractor will connect directly to the browser Drivers
  // at the locations specified by chromeDriver and firefoxPath. Only Chrome
  // and Firefox are supported for direct connect.
  directConnect: USER_CONFIG.directConnect || false,
  // Path to the firefox application binary. If null, will attempt to find
  // firefox in the default locations.
  firefoxPath: USER_CONFIG.firefoxPath || null,

  // **DEPRECATED**
  // If true, only ChromeDriver will be started, not a Selenium Server.
  // This should be replaced with directConnect.
  chromeOnly: USER_CONFIG.chromeOnly || false,

  // ---------------------------------------------------------------------------
  // ----- What tests to run ---------------------------------------------------
  // ---------------------------------------------------------------------------

  // Spec patterns are specified from the IDE based on the active selection.
  specs: SPECS,

  // Patterns to exclude.
  exclude: USER_CONFIG.exclude || [],

  // Alternatively, suites may be used. When run without a command line
  // parameter, all suites will run. If run with --suite=smoke or
  // --suite=smoke,full only the patterns matched by the specified suites will
  // run.
  suites: USER_CONFIG.suites,

  // ---------------------------------------------------------------------------
  // ----- How to set up browsers ----------------------------------------------
  // ---------------------------------------------------------------------------
  //
  // Protractor can launch your tests on one or more browsers. If you are
  // testing on a single browser, use the capabilities option. If you are
  // testing on multiple browsers, use the multiCapabilities array.

  // For a list of available capabilities, see
  // https://code.google.com/p/selenium/wiki/DesiredCapabilities
  //
  // In addition, you may specify count, shardTestFiles, and maxInstances.
  capabilities: USER_CONFIG.capabilities || {
    browserName: 'chrome',

    // Number of times to run this set of capabilities (in parallel, unless
    // limited by maxSessions). Default is 1.
    count: 1,

    // If this is set to be true, specs will be sharded by file (i.e. all
    // files to be run by this set of capabilities will run in parallel).
    // Default is false.
    shardTestFiles: false,

    // Maximum number of browser instances that can run in parallel for this
    // set of capabilities. This is only needed if shardTestFiles is true.
    // Default is 1.
    maxInstances: 1,

    // Additional spec files to be run on this capability only.
    specs: ['spec/chromeOnlySpec.js'],

    // Spec files to be excluded on this capability only.
    exclude: ['spec/doNotRunInChromeSpec.js']

  },

  // If you would like to run more than one instance of WebDriver on the same
  // tests, use multiCapabilities, which takes an array of capabilities.
  // If this is specified, capabilities will be ignored.
  multiCapabilities: USER_CONFIG.multiCapabilities || [],

  // Maximum number of total browser sessions to run. Tests are queued in
  // sequence if number of browser sessions is limited by this parameter.
  // Use a number less than 1 to denote unlimited. Default is unlimited.
  maxSessions: USER_CONFIG.maxSessions || -1,

  // ---------------------------------------------------------------------------
  // ----- Global test information ---------------------------------------------
  // ---------------------------------------------------------------------------
  //
  // A base URL for your application under test. Calls to protractor.get()
  // with relative paths will be prepended with this.
  baseUrl: USER_CONFIG.baseUrl || 'http://localhost:9876',

  // CSS Selector for the element housing the angular app - this defaults to
  // body, but is necessary if ng-app is on a descendant of <body>.
  rootElement: USER_CONFIG.rootElement || 'body',

  // The timeout in milliseconds for each script run on the browser. This should
  // be longer than the maximum time your application needs to stabilize between
  // tasks.
  allScriptsTimeout: USER_CONFIG.allScriptsTimeout || 11000,

  // How long to wait for a page to load.
  getPageTimeout: USER_CONFIG.getPageTimeout || 10000,

  // A callback function called once configs are read but before any environment
  // setup. This will only run once, and before onPrepare.
  // You can specify a file containing code to run by setting beforeLaunch to
  // the filename string.
  beforeLaunch: function() {
    // At this point, global variable 'protractor' object will NOT be set up, 
    // and globals from the test framework will NOT be available. The main
    // purpose of this function should be to bring up test dependencies.
    if(typeof USER_CONFIG.beforeLaunch === 'function') {
        USER_CONFIG.beforeLaunch();
    }
  },

  // A callback function called once protractor is ready and available, and
  // before the specs are executed.
  // If multiple capabilities are being run, this will run once per
  // capability.
  // You can specify a file containing code to run by setting onPrepare to
  // the filename string.
  onPrepare: function() {
    // At this point, global variable 'protractor' object will be set up, and
    // globals from the test framework will be available. For example, if you
    // are using Jasmine, you can add a reporter with:
    if(typeof USER_CONFIG.onPrepare === 'function') {
      USER_CONFIG.onPrepare();
    }
    require(JASMINE_NB_REPORTER);
    jasmine.getEnv().addReporter(new jasmine.NetbeansReporter());
  },

  // A callback function called once tests are finished.
  onComplete: function() {
    // At this point, tests will be done but global objects will still be
    // available.
    if(typeof USER_CONFIG.onComplete === 'function') {
      USER_CONFIG.onComplete();
    }
  },

  // A callback function called once the tests have finished running and
  // the WebDriver instance has been shut down. It is passed the exit code
  // (0 if the tests passed). This is called once per capability.
  onCleanUp: function(exitCode) {
    if(typeof USER_CONFIG.onCleanUp === 'function') {
      USER_CONFIG.onCleanUp(exitCode);
    }
  },

  // A callback function called once all tests have finished running and
  // the WebDriver instance has been shut down. It is passed the exit code
  // (0 if the tests passed). This is called only once before the program
  // exits (after onCleanUp).
  afterLaunch: function() {
    if(typeof USER_CONFIG.afterLaunch === 'function') {
      USER_CONFIG.afterLaunch();
    }
  },

  // The params object will be passed directly to the Protractor instance,
  // and can be accessed from your test as browser.params. It is an arbitrary
  // object and can contain anything you may need in your test.
  // This can be changed via the command line as:
  //   --params.login.user 'Joe'
  params: USER_CONFIG.params || {
    login: {
      user: 'Jane',
      password: '1234'
    }
  },

  // If set, protractor will save the test output in json format at this path.
  // The path is relative to the location of this config.
  resultJsonOutputFile: USER_CONFIG.resultJsonOutputFile || null,

  // If true, protractor will restart the browser between each test. 
  // CAUTION: This will cause your tests to slow down drastically.
  restartBrowserBetweenTests: USER_CONFIG.restartBrowserBetweenTests || false,

  // ---------------------------------------------------------------------------
  // ----- The test framework --------------------------------------------------
  // ---------------------------------------------------------------------------

  // Test framework to use. This may be jasmine, cucumber, or mocha.
  //
  // Jasmine is fully supported as a test and assertion framework.
  // Mocha and Cucumber have limited beta support. You will need to include your
  // own assertion framework (such as Chai) if working with Mocha.
  framework: USER_CONFIG.framework || 'jasmine',

  // Options to be passed to minijasminenode.
  //
  // See the full list at https://github.com/juliemr/minijasminenode/tree/jasmine1
  jasmineNodeOpts: USER_CONFIG.jasmineNodeOpts || {
    // If true, display spec names.
    isVerbose: true,
    // If true, print colors to the terminal.
    showColors: true,
    // If true, include stack traces in failures.
    includeStackTrace: true,
    // Default time to wait in ms before a test fails.
    defaultTimeoutInterval: 30000
  },

  // Options to be passed to Mocha.
  //
  // See the full list at http://visionmedia.github.io/mocha/
  mochaOpts: USER_CONFIG.mochaOpts || {
    ui: 'bdd',
    reporter: 'list'
  },

  // Options to be passed to Cucumber.
  cucumberOpts: USER_CONFIG.cucumberOpts || {
    // Require files before executing the features.
    require: 'cucumber/stepDefinitions.js',
    // Only execute the features or scenarios with tags matching @dev.
    // This may be an array of strings to specify multiple tags to include.
    tags: '@dev',
    // How to format features (default: progress)
    format: 'summary'
  }
};

