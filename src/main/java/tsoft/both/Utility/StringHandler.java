package tsoft.both.Utility;

import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;
import tsoft.backend.objects.service.ServiceObjects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {



    public static void saveParameter(String nameParam, String value)
    {
        if (!nameParam.equals(""))
            ServiceObjects.parameters.put(nameParam,value);
    }

    public static String changeStringWithParameters(String source)
    {
        String formatString = source;
        String macthValue = regExprExtractor("echo %(.+?)%",formatString,1);
        while (!macthValue.isEmpty())
        {
            formatString = formatString.replace("echo %"+macthValue+"%", ServiceObjects.parameters.get(macthValue));
            macthValue = regExprExtractor("echo %(.+?)%",formatString,1);
        }
        return formatString;
    }

    public static String regExprExtractor(String expression, String source, int group)
    {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(source);
        return matcher.find() ? matcher.group(group) : "";
    }

    public static HashMap<String, String> extractValues(String data) throws IOException {
        String dataProcess;
        Map<String,String> mapaDatos;
        int separator;

        dataProcess = prepararData(data);
        dataProcess = dataProcess
                .replace("\n","")
                .replace("\r","");

        separator = dataProcess.contains("&") ? 1 : dataProcess.contains(",") ? 2 : 0;
        mapaDatos = Splitter.onPattern("[&,]").omitEmptyStrings().withKeyValueSeparator(separator == 1 ? "=" : ":").split(dataProcess);
        return new HashMap<>(mapaDatos);
    }

    public static String prepararData(String data) throws IOException {
        String dataProcess;
        if (!data.contains("ARCHIVO")) {
            dataProcess = data;
        } else {
            String archivo = data.split("\"")[1].trim();
            if (!archivo.contains(":"))
                archivo = FileHelper.getProjectFolder() + "/src/test/resources/extrafiles/" + archivo;
            dataProcess = FileUtils.readFileToString(new File(archivo), StandardCharsets.UTF_8);
        }

        if (dataProcess.contains("echo %"))
            dataProcess = changeStringWithParameters(dataProcess);

        return dataProcess;
    }
}
