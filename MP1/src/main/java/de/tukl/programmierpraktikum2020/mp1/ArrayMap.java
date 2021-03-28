package de.tukl.programmierpraktikum2020.mp1;

public class ArrayMap implements Map <String,Integer> {

    //private String key;
    //private Integer value;
    private Element[] Arrayelements;

    public ArrayMap() {
        Arrayelements = new Element[0];
    }


    @Override
    public Integer get(String key) {
        Integer target = null;
        for(int i=0;i<Arrayelements.length;i++) {
            if (Arrayelements[i].getKey() == key) {
                target = (Integer) Arrayelements[i].getValue();
                break;
            }
        }
        return target;
    }

    @Override
    public void put(String key, Integer value) {
        String target = null;
        int i;
        for(i=0;i<Arrayelements.length;i++) {
            if(Arrayelements[i].getKey() == key) {
                target = key;
                break;
            }
        }
        if (target==null) {
            Element[] newArray = new Element[Arrayelements.length+1];
            for(int j=0;i<Arrayelements.length;i++) {
                newArray[i] = Arrayelements[i];
            }
            newArray[newArray.length-1] = new Element(key,value);
            Arrayelements = newArray;
        }
        else {
            Arrayelements[i].setValue(value);
        }
    }

    @Override
    public void remove(String key) {
        int i;
        for(i=0;i<Arrayelements.length;i++) {
            if (Arrayelements[i].getKey() == key) {
                Element[] newArray = new Element[Arrayelements.length-1];
                for(int j=0;j<i;j++) {
                    newArray[j] = Arrayelements[j];
                }
                for(int j=i+1;j<Arrayelements.length;j++){
                    newArray[j-1] = Arrayelements[j];
                }
                Arrayelements = newArray;
            }
        }
    }

    @Override
    public int size() {
        return Arrayelements.length;
    }

    @Override
    public void keys(String[] array) {
        if (array.length < Arrayelements.length) {
            throw new IllegalArgumentException("Array zu kurz!");
        }
        else {
            for (int i=0;i<Arrayelements.length;i++) {
                array[i] = Arrayelements[i].getKey();
            }
        }
    }
}
