package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main3 {

    public static void main(String[] args) throws ParseException {
         Options options = new Options();

        Option classPath = Option.builder("classpath").longOpt("class-path").hasArg().desc("Class Path").build();

        options.addOption(classPath);
        
        Option module = Option.builder("m").longOpt("module").hasArg().desc("Modules").build();

        options.addOption(module);

        Option group=Option.builder("g").hasArg().desc("Global").build();
        options.addOption(group);

        Option version = Option.builder("v").hasArg().longOpt("version").desc("Version").build();

        options.addOption(version);


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if(cmd.hasOption(classPath.getOpt())){
            System.out.println("Class Path : "+cmd.getOptionValue(classPath.getOpt()));
        }

        if(cmd.hasOption(module.getOpt())){
            System.out.println("Module : "+cmd.getOptionValue(module.getOpt()));
        }

        if(cmd.hasOption(group.getOpt())){
            System.out.println("Group : "+cmd.getOptionValue(group.getOpt()));
        }

        if(cmd.hasOption(version.getOpt())){
            System.out.println("Version : "+cmd.getOptionValue(version.getOpt()));
        }


        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp( "ant", options ); 



    }

}