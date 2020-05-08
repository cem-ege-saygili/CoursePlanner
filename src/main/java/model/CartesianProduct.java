package model;

import java.util.ArrayList;
import java.util.List;

public class CartesianProduct {

    public static <T> List<List<T>> GenerateCartesianProducts(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = GenerateCartesianProducts(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    if(condition instanceof Class){//For generating compatible class bundles (i.e. list of classes)
                        Class curClass = (Class) condition;
                        List<Class> classList2Compare = (List<Class>) remainingList;
                        if(curClass.isCompatibleWithClassList(classList2Compare)){
                            resultList.add(condition);
                            resultList.addAll(remainingList);
                            resultLists.add(resultList);
                        }
                    }else if(condition instanceof ClassBundle){//For generating compatible schedules (i.e. list of class bundles)
                        ClassBundle curClassBundle = (ClassBundle) condition;
                        List<ClassBundle> classBundleList2Compare = (List<ClassBundle>) remainingList;
                        if(curClassBundle.isCompatibleWithClassBundleList(classBundleList2Compare)){
                            resultList.add(condition);
                            resultList.addAll(remainingList);
                            resultLists.add(resultList);
                        }
                    }else{
                        resultList.add(condition);
                        resultList.addAll(remainingList);
                        resultLists.add(resultList);
                    }
                }
            }
        }
        return resultLists;
    }

}
