import org.jdom2.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.IdentityHashMap;

public class Serializer {
    public static Document initializeSerialization(Object object) {

        IdentityHashMap objectHashMap = new IdentityHashMap<>();
        Element rootElem = new Element("Serialized");
        Document doc = new Document(rootElem);
        Document serializationDoc = serializationOfObject(object, doc, objectHashMap);
        return serializationDoc;
    }
    public static Document serializationOfObject(Object object, Document doc, IdentityHashMap objectHashMap){
        Class classObject = object.getClass();
        ArrayList<Element> referencesInArray = new ArrayList<>();
        ArrayList<Element> valuesOfArray = new ArrayList<>();
        ArrayList<Element> elementsInfo = new ArrayList<>();
        Element fieldElem, refElem, objectElem, valElem;
        try {
            String objectID = Integer.toString(objectHashMap.size());
            objectHashMap.put(object, objectID);
            objectElem = new Element("object");
            objectElem.setAttribute("class", classObject.getName());
            objectElem.setAttribute("id", objectID);
            doc.getRootElement().addContent(objectElem);
            if (classObject.isArray()) {
                int lengthOfArrayInt = Array.getLength(object);
                String lengthOfArrayString = String.valueOf(lengthOfArrayInt);
                objectElem.setAttribute("length", lengthOfArrayString);
                Class typeOfArray = classObject.getComponentType();
                for (int i = 0; i < lengthOfArrayInt; i++) {
                    if (typeOfArray.isPrimitive()) {
                        valElem = new Element("value");
                        String valueOfElement = String.valueOf(Array.get(object, i));
                        valElem.addContent(valueOfElement);
                        valuesOfArray.add(valElem);
                        elementsInfo = valuesOfArray;
                    } else {
                        refElem = new Element("reference");
                        String arrayObjectID = Integer.toString(objectHashMap.size());
                        refElem.addContent(arrayObjectID);
                        referencesInArray.add(refElem);
                        elementsInfo = referencesInArray;
                        if (!objectHashMap.containsKey(arrayObjectID)) {
                            Object arrayObjectElem = Array.get(object, i);
                            serializationOfObject(arrayObjectElem, doc, objectHashMap);
                        }
                    }
                }
                objectElem.setContent(elementsInfo);
            }
            Field[] fieldsOfObject = classObject.getDeclaredFields();
            for (Field field : fieldsOfObject) {
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                Object objectField = field.get(object);
                fieldElem = serializationOfField(objectField, field, doc, objectHashMap);
                objectElem.addContent(fieldElem);
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return doc;
    }
    private static Element serializationOfField(Object objectField, Field field, Document doc, IdentityHashMap objectHashMap){
        Element fieldElem;
        if (!Modifier.isPublic(field.getModifiers())) field.setAccessible(true);
        if (objectField != null)
        {
            fieldElem = new Element("field");
    try{
            fieldElem.setAttribute("name", field.getName());
            fieldElem.setAttribute("declaringclass", field.getDeclaringClass().getName());
            Element refElem = new Element("reference");
            Element valElem = new Element("value");
            Class typeOfField = field.getType();
            if (!typeOfField.isPrimitive()){
                refElem.addContent(Integer.toString(objectHashMap.size()));
                fieldElem.setContent(refElem);
                serializationOfObject(objectField,doc,objectHashMap);
            }
            else {
                valElem.addContent(objectField.toString());
                fieldElem.setContent(valElem);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            fieldElem = new Element("null");
        }
        return fieldElem;
    }
}
