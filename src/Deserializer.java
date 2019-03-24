import org.jdom2.*;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Deserializer {
    public static Object deserialize(Document doc) {


        Element rootElem = doc.getRootElement();
        List listOfObjects = rootElem.getChildren();
        HashMap objectHashMap = new HashMap();
        Object object = null;
        try {
            makeObjectInstance(objectHashMap, listOfObjects);
            setFieldval(objectHashMap, listOfObjects);
            object = objectHashMap.get("0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    private static void setFieldval(HashMap objectHashMap, List listOfObject) {
        for (int i = 0; i < listOfObject.size(); i++) {

            try {
                Element objectElem = (Element) listOfObject.get(i);
                Object instanceOfObject = objectHashMap.get(objectElem.getAttributeValue("id"));
                List listOfChildrenObjects = objectElem.getChildren();
                Class classObject = instanceOfObject.getClass();
                if (classObject.isArray()) {
                    Class arrayType = classObject.getComponentType();
                    for (int j = 0; j < listOfChildrenObjects.size(); j++) {
                        Element aCE = (Element) listOfChildrenObjects.get(j);
                        Object contentOfArray = deserializationOfContentElement(arrayType, aCE,
                                objectHashMap);
                        Array.set(instanceOfObject, j, contentOfArray);
                    }
                } else {
                    for (int j = 0; j < listOfChildrenObjects.size(); j++) {
                        Element fieldElem = (Element) listOfChildrenObjects.get(j);
                        Class declaringClass = Class.forName(fieldElem.getAttributeValue("declaringclass"));
                        Field field = declaringClass.getDeclaredField(fieldElem.getAttributeValue("name"));
                        if (!Modifier.isPublic(field.getModifiers())) {
                            field.setAccessible(true);
                            Field modifiersField = Field.class.getDeclaredField("modifiers");
                            modifiersField.setAccessible(true);
                            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                        }
                        Class fieldType = field.getType();
                        Element fieldContentElement = (Element) fieldElem.getChildren().get(0);
                        Object fieldContent = deserializationOfContentElement(fieldType, fieldContentElement, objectHashMap);
                        field.set(instanceOfObject, fieldContent);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void makeObjectInstance(HashMap objectHashMap, List listOfObjects) {
        for (int i = 0; i < listOfObjects.size(); i++) {

            try {
                Object instanceOfObject;
                Element objectElem = (Element) listOfObjects.get(i);
                Class classOfObject = Class.forName(objectElem.getAttributeValue("class"));
                if (classOfObject.isArray()) {
                    instanceOfObject = Array.newInstance(classOfObject.getComponentType(),
                            Integer.parseInt(objectElem.getAttributeValue("length")));
                } else {
                    Constructor constructor = classOfObject.getConstructor(null);
                    if (!Modifier.isPublic(constructor.getModifiers())) {
                        constructor.setAccessible(true);
                    }
                    instanceOfObject = constructor.newInstance(null);
                }
                objectHashMap.put(objectElem.getAttributeValue("id"), instanceOfObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    private static Object deserializationOfFieldValue(Element valElem, Class typeOfField) {

        Object valueObject = null;
        if (typeOfField.equals(byte.class)) valueObject = Byte.valueOf(valElem.getText());
        else if (typeOfField.equals(short.class)) valueObject = Short.valueOf(valElem.getText());
        else if (typeOfField.equals(int.class)) valueObject = Integer.valueOf(valElem.getText());
        else if (typeOfField.equals(long.class)) valueObject = Long.valueOf(valElem.getText());
        else if (typeOfField.equals(float.class)) valueObject = Float.valueOf(valElem.getText());
        else if (typeOfField.equals(double.class)) valueObject = Double.valueOf(valElem.getText());
        else if (typeOfField.equals(boolean.class)) {

            if (valElem.getText().equals("true"))
                valueObject = Boolean.TRUE;
            else
                valueObject = Boolean.FALSE;
        }
        return valueObject;
    }
    private static Object deserializationOfContentElement(Class typeOfClass, Element contentElem, HashMap ObjectHashMap) {
        Object contentObject;
        if ((contentElem.getName()).equals("reference")) contentObject = ObjectHashMap.get(contentElem.getText());
        else if ((contentElem.getName()).equals("value")) contentObject = deserializationOfFieldValue(contentElem, typeOfClass);
        else contentObject = contentElem.getText();
        return contentObject;
    }

}