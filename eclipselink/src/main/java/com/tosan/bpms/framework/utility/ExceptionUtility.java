package com.tosan.bpms.framework.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by kasra.haghpanah on 14/05/2018.
 */
public class ExceptionUtility {

    public static String stackTraceToString(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

}
