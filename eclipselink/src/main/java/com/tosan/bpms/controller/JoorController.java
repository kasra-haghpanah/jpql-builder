package com.tosan.bpms.controller;


//import org.joor.Reflect;


/**
 * Created by kasra.haghpanah on 21/03/2019.
 */
public class JoorController {

    public static void main(String[] args) {

        String javaCode = "package com.tosan.bpms.controller;\n" +
                "\n" +
                "public class Test implements java.util.function.Supplier<String>{\n" +
                "\n" +
                "    public String get(){\n" +
                "        return \"Your name is kasra\" ;\n" +
                "    }\n" +
                "public String create(String fname){return \"Your name is \" + fname;}" +
                "\n" +
                "}\n";

//        Reflect compile = Reflect.compile("com.tosan.bpms.controller.Test",javaCode);
//
//        Supplier<String> supplier = compile.create().get();
//        System.out.println(supplier.get());
//
//        String result = compile.create().call("create" , "kasra haghpanah" ).get();
//        System.out.println(result);
//
//
//       List<String> list = Reflect.on(UserController.class).create().call("test","kasra","haghpanah").get();
//
//       System.out.println(list);
//
//        Reflect.on(UserController.class).call("main" /*, new String[]{"arg", "arg1"}*/);


    }


}
