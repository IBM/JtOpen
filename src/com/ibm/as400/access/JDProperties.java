///////////////////////////////////////////////////////////////////////////////
//                                                                             
// AS/400 Toolbox for Java - OSS version                                       
//                                                                             
// Filename: JDProperties.java
//                                                                             
// The source code contained herein is licensed under the IBM Public License   
// Version 1.0, which has been approved by the Open Source Initiative.         
// Copyright (C) 1997-2000 International Business Machines Corporation and     
// others. All rights reserved.                                                
//                                                                             
///////////////////////////////////////////////////////////////////////////////

package com.ibm.as400.access;

import java.io.Serializable;
import java.sql.DriverPropertyInfo;
import java.util.Enumeration;
import java.util.Properties;



/**
<p>A class representing the properties passed as connection
attributes for the driver.
**/
//
// Implementation notes:
//
// 1. If adding or changing any properties, please add
//    corresponding documentation to javadoc in JDBCProperties.html.
//    In addition, please add a desciption in
//    JDMRI.properties, which matches the first sentence
//    in the description in JDBCProperties.html.
//
// 2. We only store the key to the descriptions.  We only
//    load the actual descriptions when the caller asks
//    for them.  This saves the descriptions from being
//    loaded in all cases.
//
class JDProperties implements Serializable {
  private static final String copyright = "Copyright (C) 1997-2000 International Business Machines Corporation and others.";




    // Callers should access the properties using one of the
    // following indicies.
    //
    static final int		    ACCESS      			= 0;
    static final int		    BLOCK_SIZE		        = 1;
    static final int		    BLOCK_CRITERIA	        = 2;
    static final int		    DATE_FORMAT             = 3;
    static final int		    DATE_SEPARATOR          = 4;
    static final int		    DECIMAL_SEPARATOR       = 5;
    static final int            ERRORS                  = 6;
    static final int            EXTENDED_DYNAMIC        = 7;
	static final int		    LIBRARIES           	= 8;
	static final int		    NAMING          		= 9;
	static final int		    PACKAGE     			= 10;
	static final int            PACKAGE_ADD             = 11;
	static final int            PACKAGE_CACHE           = 12;
	static final int            PACKAGE_CLEAR           = 13;
	static final int            PACKAGE_ERROR           = 14;
	static final int            PACKAGE_LIBRARY         = 15;
	static final int		    PASSWORD				= 16;
	static final int		    PREFETCH				= 17;
	static final int            PROMPT                  = 18;
    static final int		    REMARKS	                = 19;
    static final int            SORT                    = 20;
    static final int            SORT_LANGUAGE           = 21;
    static final int            SORT_TABLE              = 22;
    static final int            SORT_WEIGHT             = 23;
    static final int		    TIME_FORMAT             = 24;
    static final int		    TIME_SEPARATOR          = 25;
    static final int            TRACE                   = 26;
	static final int		    TRANSACTION_ISOLATION	= 27;
	static final int            TRANSLATE_BINARY        = 28;
	static final int		    USER    				= 29;
	static final int		    PACKAGE_CRITERIA        = 30;      // @A0A
	static final int		    LOB_THRESHOLD           = 31;
	static final int		    SECURE                  = 32;
    static final int            DATA_TRUNCATION         = 33;       // @C1A
    static final int		    PROXY_SERVER            = 34;   // @A3A
  //static final int		    PROXY_SERVER_SECURE     = 35;   // @A3A
    static final int		    SECONDARY_URL           = 35;   // @A3A
    static final int            DATA_COMPRESSION        = 36;   // @D0A
   static final int         CURSOR_HOLD             = 37;       // @D1

	private static final int    NUMBER_OF_ATTRIBUTES_	= 38;      // @A0C @C1C @A3A @D0C @D1



    // Property names.
    private static final String ACCESS_                 = "access";
    private static final String BLOCK_SIZE_             = "block size";
    private static final String BLOCK_CRITERIA_         = "block criteria";
    private static final String CURSOR_HOLD_            = "cursor hold";            // @D1
    private static final String CURSORHOLD_             = "CURSORHOLD";             // @D1
    private static final String DATA_COMPRESSION_       = "data compression";       // @D0A
    private static final String DATA_TRUNCATION_        = "data truncation";        // @C1A
    private static final String DATE_FORMAT_            = "date format";
    private static final String DATE_SEPARATOR_         = "date separator";
    private static final String DECIMAL_SEPARATOR_      = "decimal separator";
    private static final String ERRORS_                 = "errors";
    private static final String EXTENDED_DYNAMIC_       = "extended dynamic";
    private static final String LIBRARIES_              = "libraries";
    private static final String LOB_THRESHOLD_          = "lob threshold";
    private static final String NAMING_                 = "naming";
    private static final String PACKAGE_                = "package";
    private static final String PACKAGE_ADD_            = "package add";
    private static final String PACKAGE_CACHE_          = "package cache";
    private static final String PACKAGE_CLEAR_          = "package clear";
    private static final String PACKAGE_CRITERIA_       = "package criteria";        // @A0A
    private static final String PACKAGE_ERROR_          = "package error";
    private static final String PACKAGE_LIBRARY_        = "package library";
    private static final String PASSWORD_               = "password";
    private static final String PREFETCH_               = "prefetch";
    private static final String PROMPT_                 = "prompt";
    private static final String PROXY_SERVER_           = "proxy server";        // @A3A
  //private static final String PROXY_SERVER_SECURE_    = "proxy server secure"; // @A3A
    private static final String REMARKS_                = "remarks";
            static final String SECONDARY_URL_          = "secondary URL";       // @A3A
    private static final String SECURE_                 = "secure";
    private static final String SORT_                   = "sort";
    private static final String SORT_LANGUAGE_          = "sort language";
    private static final String SORT_TABLE_             = "sort table";
    private static final String SORT_WEIGHT_            = "sort weight";
    private static final String TIME_FORMAT_            = "time format";
    private static final String TIME_SEPARATOR_         = "time separator";
    private static final String TRACE_                  = "trace";
    private static final String TRANSACTION_ISOLATION_  = "transaction isolation";
    private static final String TRANSLATE_BINARY_       = "translate binary";
    private static final String USER_                   = "user";




    // Common String objects.  Using these will theoretically
    // cut down on the number of String allocations.
    //
    private static final String COMMA_                  = ",";
    private static final String EMPTY_                  = "";
    private static final String EUR_                    = "eur";
    private static final String FALSE_                  = "false";
    private static final String ISO_                    = "iso";
    private static final String JIS_                    = "jis";
    private static final String NONE_                   = "none";
    private static final String PERIOD_                 = ".";
    private static final String SPACE_                  = "b";
    private static final String SQL_                    = "sql";
    private static final String SYSTEM_                 = "system";
    private static final String TRUE_                   = "true";
    private static final String USA_                    = "usa";



    // Callers compare property values against valid choices
    // using the following constants.
    //
    static final String         ACCESS_ALL                      = "all";
    static final String         ACCESS_READ_CALL                = "read call";
    static final String         ACCESS_READ_ONLY                = "read only";

    static final String         BLOCK_CRITERIA_NONE             = "0";
    static final String         BLOCK_CRITERIA_IF_FETCH         = "1";
    static final String         BLOCK_CRITERIA_UNLESS_UPDATE    = "2";

    static final String         BLOCK_SIZE_0                    = "0";
    static final String         BLOCK_SIZE_8                    = "8";
    static final String         BLOCK_SIZE_16                   = "16";
    static final String         BLOCK_SIZE_32                   = "32";
    static final String         BLOCK_SIZE_64                   = "64";
    static final String         BLOCK_SIZE_128                  = "128";
    static final String         BLOCK_SIZE_256                  = "256";
    static final String         BLOCK_SIZE_512                  = "512";

    static final String         DATE_FORMAT_JULIAN              = "julian";
    static final String         DATE_FORMAT_MDY                 = "mdy";
    static final String         DATE_FORMAT_DMY                 = "dmy";
    static final String         DATE_FORMAT_YMD                 = "ymd";
    static final String         DATE_FORMAT_USA                 = "usa";
    static final String         DATE_FORMAT_ISO                 = ISO_;
    static final String         DATE_FORMAT_EUR                 = EUR_;
    static final String         DATE_FORMAT_JIS                 = JIS_;
    static final String         DATE_FORMAT_NOTSET              = EMPTY_;

    static final String         DATE_SEPARATOR_SLASH            = "/";
    static final String         DATE_SEPARATOR_DASH             = "-";
    static final String         DATE_SEPARATOR_PERIOD           = PERIOD_;
    static final String         DATE_SEPARATOR_COMMA            = COMMA_;
    static final String         DATE_SEPARATOR_SPACE            = SPACE_;
    static final String         DATE_SEPARATOR_NOTSET           = EMPTY_;

    static final String         DECIMAL_SEPARATOR_COMMA         = COMMA_;
    static final String         DECIMAL_SEPARATOR_PERIOD        = PERIOD_;
    static final String         DECIMAL_SEPARATOR_NOTSET        = EMPTY_;

    static final String         ERRORS_BASIC                    = "basic";
    static final String         ERRORS_FULL                     = "full";

    static final String         NAMING_SQL                      = SQL_;
    static final String         NAMING_SYSTEM                   = SYSTEM_;

    static final String         PACKAGE_ERROR_EXCEPTION         = "exception";
    static final String         PACKAGE_ERROR_NONE              = NONE_;
    static final String         PACKAGE_ERROR_WARNING           = "warning";

    static final String         REMARKS_SQL                     = SQL_;
    static final String         REMARKS_SYSTEM                  = SYSTEM_;

    static final String         SORT_HEX                        = "hex";
    static final String         SORT_JOB                        = "job";
    static final String         SORT_LANGUAGE1                  = "language";
    static final String         SORT_TABLE1                     = "table";

    static final String         SORT_WEIGHT_SHARED              = "shared";
    static final String         SORT_WEIGHT_UNIQUE              = "unique";

    static final String         TIME_FORMAT_HMS                 = "hms";
    static final String         TIME_FORMAT_USA                 = USA_;
    static final String         TIME_FORMAT_ISO                 = ISO_;
    static final String         TIME_FORMAT_EUR                 = EUR_;
    static final String         TIME_FORMAT_JIS                 = JIS_;
    static final String         TIME_FORMAT_NOTSET              = EMPTY_;

    static final String         TIME_SEPARATOR_COLON            = ":";
    static final String         TIME_SEPARATOR_PERIOD           = PERIOD_;
    static final String         TIME_SEPARATOR_COMMA            = COMMA_;
    static final String         TIME_SEPARATOR_SPACE            = SPACE_;
    static final String         TIME_SEPARATOR_NOTSET           = EMPTY_;

	static final String         TRANSACTION_ISOLATION_NONE                  = NONE_;
	static final String         TRANSACTION_ISOLATION_READ_COMMITTED        = "read committed";
	static final String         TRANSACTION_ISOLATION_READ_UNCOMMITTED      = "read uncommitted";
	static final String         TRANSACTION_ISOLATION_REPEATABLE_READ       = "repeatable read";
	static final String         TRANSACTION_ISOLATION_SERIALIZABLE          = "serializable";

	static final String         PACKAGE_CRITERIA_DEFAULT           = "default";   // @A0A
	static final String         PACKAGE_CRITERIA_SELECT            = "select";    // @A0A

   static final String              CURSORHOLD_FALSE             = "FALSE";    // @D1
   static final String              CURSORHOLD_TRUE              = "TRUE";     // @D1
   private static final String      CURSORHOLD_NO                = "0";        // @D1
   private static final String      CURSORHOLD_YES               = "1";        // @D1

   static final String              TRACE_SET_ON                 = "TRUE";           // @E7
   static final String              TRACE_SET_OFF                = "FALSE";          // @E7
   static final String              TRACE_NOT_SPECIFIED          = "NOT SPECIFIED";  // @E7

    // Static data.
	private static DriverPropertyInfo[] dpi_;
	private static String[]             defaults_;



    // Private data.
	private boolean             extra_;
	private String[]            values_;
	private Properties          info_;   // @A3A



/**
Static initializer.
**/
    static
    {
        // Initialize.
		dpi_ = new DriverPropertyInfo[NUMBER_OF_ATTRIBUTES_];
		defaults_ = new String[NUMBER_OF_ATTRIBUTES_];
		int i;

		// Access.
		i = ACCESS;
		dpi_[i] = new DriverPropertyInfo (ACCESS_, "");
		dpi_[i].description = "ACCESS_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[3];
		dpi_[i].choices[0]	= ACCESS_ALL;
		dpi_[i].choices[1]	= ACCESS_READ_CALL;
		dpi_[i].choices[2]	= ACCESS_READ_ONLY;
		defaults_[i]        = ACCESS_ALL;

		// Block criteria.
		i = BLOCK_CRITERIA;
		dpi_[i] = new DriverPropertyInfo (BLOCK_CRITERIA_, "");
		dpi_[i].description = "BLOCK_CRITERIA_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[3];
		dpi_[i].choices[0]	= BLOCK_CRITERIA_NONE;
		dpi_[i].choices[1]	= BLOCK_CRITERIA_IF_FETCH;
		dpi_[i].choices[2]	= BLOCK_CRITERIA_UNLESS_UPDATE;
        defaults_[i]        = BLOCK_CRITERIA_UNLESS_UPDATE;

		// Block size.
		i = BLOCK_SIZE;
		dpi_[i] = new DriverPropertyInfo (BLOCK_SIZE_, "");
		dpi_[i].description = "BLOCK_SIZE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[8];
		dpi_[i].choices[0]	= BLOCK_SIZE_0;
		dpi_[i].choices[1]	= BLOCK_SIZE_8;
		dpi_[i].choices[2]	= BLOCK_SIZE_16;
		dpi_[i].choices[3]	= BLOCK_SIZE_32;
		dpi_[i].choices[4]	= BLOCK_SIZE_64;
		dpi_[i].choices[5]	= BLOCK_SIZE_128;
		dpi_[i].choices[6]	= BLOCK_SIZE_256;
		dpi_[i].choices[7]	= BLOCK_SIZE_512;
        defaults_[i]        = BLOCK_SIZE_32;

        // Cursor Hold.  @D1
        i = CURSOR_HOLD;
        dpi_[i] = new DriverPropertyInfo (CURSOR_HOLD_, "");
        dpi_[i].description = "Specifies whether to hold the cursor across transactions.";      // @D1 - MRI to be added in V5R1
        dpi_[i].required	= false;
        dpi_[i].choices		= new String[2];
        dpi_[i].choices[0]	= TRUE_;
        dpi_[i].choices[1]	= FALSE_;
          defaults_[i]        = TRUE_;

        // Data compression.  @D0A
        i = DATA_COMPRESSION;
        dpi_[i] = new DriverPropertyInfo (DATA_COMPRESSION_, "");
        dpi_[i].description = "DATA_COMPRESSION_DESC";
        dpi_[i].required	= false;
        dpi_[i].choices		= new String[2];
        dpi_[i].choices[0]	= FALSE_;
        dpi_[i].choices[1]	= TRUE_;
        defaults_[i]        = FALSE_;
  
        // Data truncation.  @C1A
        i = DATA_TRUNCATION;
        dpi_[i] = new DriverPropertyInfo (DATA_TRUNCATION_, "");
        dpi_[i].description = "DATA_TRUNCATION_DESC";
        dpi_[i].required	= false;
        dpi_[i].choices		= new String[2];
        dpi_[i].choices[0]	= TRUE_;
        dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = FALSE_;
  
		// Date format.  The order that the choices are listed
		// is significant - the index matches the server value.
		// These also correspond to the constants defined in
		// SQLConversionSettings.
		i = DATE_FORMAT;
		dpi_[i] = new DriverPropertyInfo (DATE_FORMAT_, "");
		dpi_[i].description = "DATE_FORMAT_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[8];
		dpi_[i].choices[0]	= DATE_FORMAT_JULIAN;
		dpi_[i].choices[1]	= DATE_FORMAT_MDY;
		dpi_[i].choices[2]	= DATE_FORMAT_DMY;
		dpi_[i].choices[3]	= DATE_FORMAT_YMD;
		dpi_[i].choices[4]	= DATE_FORMAT_USA;
		dpi_[i].choices[5]	= DATE_FORMAT_ISO;
		dpi_[i].choices[6]	= DATE_FORMAT_EUR;
		dpi_[i].choices[7]	= DATE_FORMAT_JIS;
        defaults_[i]        = DATE_FORMAT_NOTSET;

		// Date separator.  The order that the choices are listed
		// is significant - the index matches the server value.
		i = DATE_SEPARATOR;
		dpi_[i] = new DriverPropertyInfo (DATE_SEPARATOR_, "");
		dpi_[i].description = "DATE_SEPARATOR_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[5];
		dpi_[i].choices[0]	= DATE_SEPARATOR_SLASH;
		dpi_[i].choices[1]	= DATE_SEPARATOR_DASH;
		dpi_[i].choices[2]	= DATE_SEPARATOR_PERIOD;
		dpi_[i].choices[3]	= DATE_SEPARATOR_COMMA;
		dpi_[i].choices[4]	= DATE_SEPARATOR_SPACE;
        defaults_[i]        = DATE_SEPARATOR_NOTSET;

		// Decimal separator.  The order that the choices are listed
		// is significant - the index matches the server value.
		i = DECIMAL_SEPARATOR;
		dpi_[i] = new DriverPropertyInfo (DECIMAL_SEPARATOR_, "");
		dpi_[i].description = "DECIMAL_SEPARATOR_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= DECIMAL_SEPARATOR_PERIOD;
		dpi_[i].choices[1]	= DECIMAL_SEPARATOR_COMMA;
        defaults_[i]        = DECIMAL_SEPARATOR_NOTSET;

		// Extended dynamic.
		i = EXTENDED_DYNAMIC;
		dpi_[i] = new DriverPropertyInfo (EXTENDED_DYNAMIC_, "");
		dpi_[i].description = "EXTENDED_DYNAMIC_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= TRUE_;
		dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = FALSE_;

		// Errors.
		i = ERRORS;
		dpi_[i] = new DriverPropertyInfo (ERRORS_, "");
		dpi_[i].description = "ERRORS_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[1]	= ERRORS_BASIC;
		dpi_[i].choices[0]	= ERRORS_FULL;
        defaults_[i]        = ERRORS_BASIC;

		// Libraries.
		i = LIBRARIES;
		dpi_[i] = new DriverPropertyInfo (LIBRARIES_, "");
		dpi_[i].description = "LIBRARIES_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// LOB threshold.
		i = LOB_THRESHOLD;
		dpi_[i] = new DriverPropertyInfo (LOB_THRESHOLD_, "");
		dpi_[i].description = "LOB_THRESHOLD_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = "32768";

        // Naming.  The order that the choices are listed
		// is significant - the index matches the server value.
		i = NAMING;
		dpi_[i] = new DriverPropertyInfo (NAMING_, "");
		dpi_[i].description = "NAMING_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= NAMING_SQL;
		dpi_[i].choices[1]	= NAMING_SYSTEM;
        defaults_[i]        = NAMING_SQL;

		// Package.
		i = PACKAGE;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_, "");
		dpi_[i].description = "PACKAGE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// Package add.
		i = PACKAGE_ADD;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_ADD_, "");
		dpi_[i].description = "PACKAGE_ADD_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= TRUE_;
		dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = TRUE_;

		// Package cache.
		i = PACKAGE_CACHE;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_CACHE_, "");
		dpi_[i].description = "PACKAGE_CACHE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= TRUE_;
		dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = FALSE_;

		// Package clear.
		i = PACKAGE_CLEAR;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_CLEAR_, "");
		dpi_[i].description = "PACKAGE_CLEAR_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= TRUE_;
		dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = FALSE_;

		// @A0A
		// Package criteria.
		i = PACKAGE_CRITERIA;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_CRITERIA_, "");
		dpi_[i].description = "PACKAGE_CRITERIA_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= PACKAGE_CRITERIA_DEFAULT;
		dpi_[i].choices[1]	= PACKAGE_CRITERIA_SELECT;
        defaults_[i]        = PACKAGE_CRITERIA_DEFAULT;
        // End of @A0A

		// Package error.
		i = PACKAGE_ERROR;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_ERROR_, "");
		dpi_[i].description = "PACKAGE_ERROR_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[3];
		dpi_[i].choices[0]	= PACKAGE_ERROR_NONE;
		dpi_[i].choices[1]	= PACKAGE_ERROR_WARNING;
		dpi_[i].choices[2]  = PACKAGE_ERROR_EXCEPTION;
        defaults_[i]        = PACKAGE_ERROR_WARNING;

		// Package library.
		i = PACKAGE_LIBRARY;
		dpi_[i] = new DriverPropertyInfo (PACKAGE_LIBRARY_, "");
		dpi_[i].description = "PACKAGE_LIBRARY_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// Password.
		i = PASSWORD;
		dpi_[i] = new DriverPropertyInfo (PASSWORD_, "");
		dpi_[i].description = "PASSWORD_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// Prefetch.
		i = PREFETCH;
		dpi_[i] = new DriverPropertyInfo (PREFETCH_, "");
		dpi_[i].description = "PREFETCH_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= TRUE_;
		dpi_[i].choices[1]	= FALSE_;
        defaults_[i]        = TRUE_;

		// Prompt.
		i = PROMPT;
		dpi_[i] = new DriverPropertyInfo (PROMPT_, "");
		dpi_[i].description = "PROMPT_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]  = TRUE_;
		dpi_[i].choices[1]  = FALSE_;
        defaults_[i]        = TRUE_;

		// Proxy server.    //@A3A
		i = PROXY_SERVER;
		dpi_[i] = new DriverPropertyInfo (PROXY_SERVER_, "");
		dpi_[i].description = "PROXY_SERVER_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;
		
        // Proxy server secure.    //@A3A
		//i = PROXY_SERVER_SECURE;
		//dpi_[i] = new DriverPropertyInfo (PROXY_SERVER_SECURE_, "");
		//dpi_[i].description = "PROXY_SERVER_SECURE_DESC";
		//dpi_[i].required	= false;
		//dpi_[i].choices		= new String[2];
		//dpi_[i].choices[0]  = TRUE_;
		//dpi_[i].choices[1]  = FALSE_;
        //defaults_[i]        = FALSE_;

		// Remarks.
		i = REMARKS;
		dpi_[i] = new DriverPropertyInfo (REMARKS_, "");
		dpi_[i].description = "REMARKS_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= REMARKS_SQL;
		dpi_[i].choices[1]	= REMARKS_SYSTEM;
        defaults_[i]        = REMARKS_SYSTEM;

		// Secondary URL.    //@A3A
		i = SECONDARY_URL;
		dpi_[i] = new DriverPropertyInfo (SECONDARY_URL_, "");
		dpi_[i].description = "SECONDARY_URL_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;
		
        // Secure.
		i = SECURE;
		dpi_[i] = new DriverPropertyInfo (SECURE_, "");
		dpi_[i].description = "SECURE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]  = TRUE_;
		dpi_[i].choices[1]  = FALSE_;
        defaults_[i]        = FALSE_;

		// Sort.
		i = SORT;
		dpi_[i] = new DriverPropertyInfo (SORT_, "");
		dpi_[i].description = "SORT_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[4];
		dpi_[i].choices[0]	= SORT_HEX;
		dpi_[i].choices[1]	= SORT_JOB;
		dpi_[i].choices[2]	= SORT_LANGUAGE1;
		dpi_[i].choices[3]	= SORT_TABLE1;
        defaults_[i]        = SORT_JOB;

		// Sort language.
		i = SORT_LANGUAGE;
		dpi_[i] = new DriverPropertyInfo (SORT_LANGUAGE_, "");
		dpi_[i].description = "SORT_LANGUAGE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// Sort table.
		i = SORT_TABLE;
		dpi_[i] = new DriverPropertyInfo (SORT_TABLE_, "");
		dpi_[i].description = "SORT_TABLE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

		// Sort weight.
		i = SORT_WEIGHT;
		dpi_[i] = new DriverPropertyInfo (SORT_WEIGHT_, "");
		dpi_[i].description = "SORT_WEIGHT_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]	= SORT_WEIGHT_SHARED;
		dpi_[i].choices[1]	= SORT_WEIGHT_UNIQUE;
        defaults_[i]        = SORT_WEIGHT_SHARED;

		// Time format.  The order that the choices are listed
		// is significant - the index matches the server value.
		// These also correspond to the constants defined in
		// SQLConversionSettings.
		i = TIME_FORMAT;
		dpi_[i] = new DriverPropertyInfo (TIME_FORMAT_, "");
		dpi_[i].description = "TIME_FORMAT_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[5];
		dpi_[i].choices[0]	= TIME_FORMAT_HMS;
		dpi_[i].choices[1]	= TIME_FORMAT_USA;
		dpi_[i].choices[2]	= TIME_FORMAT_ISO;
		dpi_[i].choices[3]	= TIME_FORMAT_EUR;
		dpi_[i].choices[4]	= TIME_FORMAT_JIS;
        defaults_[i]        = TIME_FORMAT_NOTSET;

		// Time separator.  The order that the choices are listed
		// is significant - the index matches the server value.
		i = TIME_SEPARATOR;
		dpi_[i] = new DriverPropertyInfo (TIME_SEPARATOR_, "");
		dpi_[i].description = "TIME_SEPARATOR_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[4];
		dpi_[i].choices[0]	= TIME_SEPARATOR_COLON;
		dpi_[i].choices[1]	= TIME_SEPARATOR_PERIOD;
		dpi_[i].choices[2]	= TIME_SEPARATOR_COMMA;
		dpi_[i].choices[3]	= TIME_SEPARATOR_SPACE;
        defaults_[i]        = TIME_SEPARATOR_NOTSET;

		// Trace.
		i = TRACE;
		dpi_[i] = new DriverPropertyInfo (TRACE_, "");
		dpi_[i].description = "TRACE_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]  = TRUE_;
		dpi_[i].choices[1]  = FALSE_;
        defaults_[i]        = FALSE_;

		// Transaction isolation.
		i = TRANSACTION_ISOLATION;
		dpi_[i] = new DriverPropertyInfo (TRANSACTION_ISOLATION_, "");
		dpi_[i].description = "TRANSACTION_ISOLATION_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[5];
		dpi_[i].choices[0]	= TRANSACTION_ISOLATION_NONE;
		dpi_[i].choices[1]	= TRANSACTION_ISOLATION_READ_COMMITTED;
		dpi_[i].choices[2]	= TRANSACTION_ISOLATION_READ_UNCOMMITTED;
		dpi_[i].choices[3]	= TRANSACTION_ISOLATION_REPEATABLE_READ;
		dpi_[i].choices[4]	= TRANSACTION_ISOLATION_SERIALIZABLE;
        defaults_[i]        = TRANSACTION_ISOLATION_READ_UNCOMMITTED;

		// Translate binary.
		i = TRANSLATE_BINARY;
		dpi_[i] = new DriverPropertyInfo (TRANSLATE_BINARY_, "");
		dpi_[i].description = "TRANSLATE_BINARY_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[2];
		dpi_[i].choices[0]  = TRUE_;
		dpi_[i].choices[1]  = FALSE_;
        defaults_[i]        = FALSE_;

		// User.
		i = USER;
		dpi_[i] = new DriverPropertyInfo (USER_, "");
		dpi_[i].description = "USER_DESC";
		dpi_[i].required	= false;
		dpi_[i].choices		= new String[0];
        defaults_[i]        = EMPTY_;

    }



/**
Constructor.

@param  urlProperties   The URL properties.
@param  info            The info properties.
**/
	JDProperties (Properties urlProperties, Properties info)
    {
        // Initialize the values.
		info_ = info;
		values_ = new String[NUMBER_OF_ATTRIBUTES_];
		for (int i = 0; i < NUMBER_OF_ATTRIBUTES_; ++i)
		    setString (i, getProperty (urlProperties, info, dpi_[i].name));

		// Check both sets of properties for any extra
		// properties.
		extra_ = false;

        Enumeration propertyNames;
        boolean found;
        String propertyName;

        if (urlProperties != null) {
            propertyNames = urlProperties.propertyNames ();
            while ((propertyNames.hasMoreElements ()) /* @C4D && (extra_ == false) */) {
                propertyName = (String) propertyNames.nextElement ();
                if (propertyName.length () > 0) {
                    found = false;
                    for (int j = 0; (j < NUMBER_OF_ATTRIBUTES_) && (! found); ++j)
                        if (propertyName.equalsIgnoreCase (dpi_[j].name))
                            found = true;

                    if (! found)                                   // @D1
                    {
                       if (propertyName.equalsIgnoreCase(CURSORHOLD_))
                       {
                          String value = getProperty (urlProperties, info, CURSORHOLD_);
                          
                          if (JDTrace.isTraceOn()) 
                             JDTrace.logInformation(this, propertyName + ": " + value);

                          if (value.equalsIgnoreCase(CURSORHOLD_YES))
                             setString(CURSOR_HOLD, TRUE_);
                          else if (value.equalsIgnoreCase(CURSORHOLD_NO))
                             setString(CURSOR_HOLD, FALSE_);                              
                       }
                       else
                       {
                          extra_ = true;
                          if (JDTrace.isTraceOn())
                              JDTrace.logInformation (this, "Extra property: \""
                                  + propertyName + "\"");
                       }
                    }
                    //if (! found) {                                 // @D1
                    //    extra_ = true;
                    //    if (JDTrace.isTraceOn())
                    //        JDTrace.logInformation (this, "Extra property: \""
                    //            + propertyName + "\"");
                    //}
                }
            }
        }

        if (info != null) {
            propertyNames = info.propertyNames ();
            while ((propertyNames.hasMoreElements ()) /* @C4D && (extra_ == false) */) {
                propertyName = (String) propertyNames.nextElement ();
                if (propertyName.length () > 0) {
                    found = false;
                    for (int j = 0; (j < NUMBER_OF_ATTRIBUTES_) && (! found); ++j)
                        if (propertyName.equalsIgnoreCase (dpi_[j].name))
                            found = true;

                    if (! found)                                   // @D1
                    {
                       if (propertyName.equalsIgnoreCase(CURSORHOLD_))
                       {
                          String value = getProperty (urlProperties, info, CURSORHOLD_);
                          
                          if (JDTrace.isTraceOn()) 
                             JDTrace.logInformation(this, propertyName + ": " + value);

                          if (value.equalsIgnoreCase(CURSORHOLD_YES))
                             setString(CURSOR_HOLD, TRUE_);
                          else if (value.equalsIgnoreCase(CURSORHOLD_NO))
                             setString(CURSOR_HOLD, FALSE_);                              
                       }
                       else
                       {
                          extra_ = true;
                          if (JDTrace.isTraceOn())
                              JDTrace.logInformation (this, "Extra property: \""
                                  + propertyName + "\"");
                       }
                    }
                    //if (! found) {                              // @D1
                    //    extra_ = true;
                    //    if (JDTrace.isTraceOn())
                    //        JDTrace.logInformation (this, "Extra property: \""
                    //            + propertyName + "\"");
                    //}
                }
            }
        }
    }



/**
Is the value of the specified property set to the specified
value?  The comparison is case insensitive.

@param      index   Property index.
@param      value   Value to compare to.
@return     true or false.
**/
    boolean equals (int index, String value)
    {
        return getString (index).equalsIgnoreCase (value);
    }




/**
Get the driver property info.

@return     The info.
**/
    DriverPropertyInfo[] getInfo ()
    {
        // Make a complete copy of the table so that if the
        // caller modifies it, it will not affect the connection.
        //
		DriverPropertyInfo[] dpi = new DriverPropertyInfo[NUMBER_OF_ATTRIBUTES_];

		for (int i = 0; i < NUMBER_OF_ATTRIBUTES_; ++i) {

		    if (i != PASSWORD)
    			dpi[i] = new DriverPropertyInfo (dpi_[i].name, values_[i]);
    	    else
    	        dpi[i] = new DriverPropertyInfo (dpi_[i].name, "");

			dpi[i].required = dpi_[i].required;
			dpi[i].choices = new String[dpi_[i].choices.length];
			for (int j = 0; j < dpi_[i].choices.length; ++j)
				dpi[i].choices[j] = dpi_[i].choices[j];

            // Load the actual description from the resource bundle.
			dpi[i].description = AS400JDBCDriver.getResource (dpi_[i].description);
		}

		return dpi;
    }



/**
Get the value of the specified property as a boolean.  This
is intended for properties that take "true" and "false" as
values.

@param      index   Property index.
@return     The value.
**/
    boolean getBoolean (int index)
    {
        return Boolean.valueOf (values_[index]).booleanValue();
    }



/**
Get the index of the value of the specified property.  This
is the index within the list of choices.  If the property is
not specified, then the index of the default value will be
returned.  If the value does not match a choice, then return -1.

@param      index   Property index.
@return     The index of the value, or -1.
**/
    int getIndex (int index)
    {
        for (int i = 0; i < dpi_[index].choices.length; ++i)
            if (values_[index].equalsIgnoreCase (dpi_[index].choices[i]))
                return i;
        return -1;
    }



/**
Get the value of the specified property as an int.  This
is intended for properties that take integers as values.

@param      index   Property index.
@return     The value.
**/
    int getInt (int index)
    {
        try {                                                               // @C2A
            return Integer.parseInt (values_[index]);
        }                                                                   // @C2A
        catch (NumberFormatException e) {                                   // @C2A
            return 0;                                                       // @C2A
        }                                                                   // @C2A
    }



// @A3A
/**
Get the original "info" Properties object, that was passed in as the
second argument to the constructor of this object.

@return     The original "info" Properties object.
**/
    Properties getOriginalInfo ()
    {
        return info_;
    }



/**
Returns the value of the specified property.  The
URL properties are searched first, then the info
properties.

@param  urlProperties   The URL properties.
@param  info            The info properties.
@param  propertyName    The property name.
**/
    private static String getProperty (Properties urlProperties,
                               Properties info,
                               String propertyName)
    {
        String value = null;
        if (urlProperties != null)
            value = urlProperties.getProperty (propertyName);
        if ((value == null) && (info != null))
            value = info.getProperty (propertyName);
        return value;
    }



/**
Get the value of the specified property.  If the property is
not specified, then the default value will be returned.  If
choices are allowed, then the value will be compared against
the choices, and if none match, the default value will be
returned.

@param      index   Property index.
@return     The value.
**/
    String getString (int index)
    {
        String value = values_[index];

        if (index == PASSWORD)
            values_[index] = "";

        return value.trim();
    }



/**
Indicates if any extra properties are specified.

@param      properties   The properties.
@return     true or false
**/
    boolean isExtraPropertySpecified ()
    {
        return extra_;
    }



/**
Indicates if the trace property is set.  This needs to be
detected before an object of this class is even instantiated,
which is why it is static.

@param      urlProperties   The URL properties.
@param      info            The info properties.
@return     Whether the trace property was set to true, false, or not specified when constructed.
**/
    static String isTraceSet (Properties urlProperties, Properties info)	//@E7C
    {
	if (getProperty (urlProperties, info, TRACE_) == null)	 
	   return TRACE_NOT_SPECIFIED;   
	else if (Boolean.valueOf(getProperty (urlProperties, info, TRACE_)).booleanValue())
	   return TRACE_SET_ON;
	else
	   return TRACE_SET_OFF;
        //@E7D return Boolean.valueOf (getProperty (urlProperties, info, TRACE_)).booleanValue();
    }



/**
Set the value of the specified property.
If choices are allowed, then the value will be compared against
the choices, and if none match, the property will be set to
the default value.

@param      index   Property index.
@param      value   The value.
**/
    void setString (int index, String value)
    {
        // If no property was provided, then set the choice
        // to the default.
        if (value == null)
            values_[index] = defaults_[index];
        else
            values_[index] = value;

        // If choices are provided, for a specified index,
        // then validate the current choice.
        if (dpi_[index].choices.length > 0) {
            boolean valid = false;
            for (int i = 0; i < dpi_[index].choices.length; ++i) {
                if (values_[index].equalsIgnoreCase (dpi_[index].choices[i])) {
                    valid = true;
                    break;
                }
            }

            // If not valid, then set the current choice to
            // the default.
            if (! valid)
                values_[index] = defaults_[index];
        }

        if (JDTrace.isTraceOn())
            JDTrace.logProperty (this, dpi_[index].name,
                ((index != PASSWORD) ? values_[index] : ""));
    }



/**
Returns the string representation of the object.

@return The string representation.
**/
//
// Implementatation note:  This is necessary only for tracing.
//
    public String toString ()
    {
        return "";
    }



}
