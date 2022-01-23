package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static Integer lostNumber = 0;
    static Integer pasadas = 0;

    public static void main(String[] args) {
        int min = 1;
        int max = 100;

        List<Integer> originalList = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
        Integer randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
        originalList.remove(randomNumber);
        System.out.println();
        System.out.println("Elemento para eliminar: " + randomNumber);
        System.out.println("Lista creada sin un elemento: " + originalList);
        getLostElement(originalList, min, max);
        System.out.println("El elemento faltante es: " + lostNumber);
    }

    public static void getLostElement(List<Integer> originalList, Integer firstIndex, Integer lastIndex){
        System.out.println("*************************************************************************** ");
        System.out.println("Pasada: " + ++pasadas);
        Integer middleIndex = (lastIndex - firstIndex) / 2;
        System.out.println("Elemento intermedio: " + middleIndex);
        List<Integer> leftSubList = originalList.subList(firstIndex - 1, middleIndex + 1);
        List<Integer> rightSubList = originalList.subList(middleIndex + 1 , originalList.size());

        Integer leftValidation = leftSubList.get(leftSubList.size() - 1) - leftSubList.get(0) + 1;
        Integer rightValidation = rightSubList.get(rightSubList.size() -1) - rightSubList.get(0) + 1;

        System.out.println("Sublista izquierda: " + leftSubList);
        System.out.println("Sublista derecha: " + rightSubList);
        System.out.println("Conteo que debería de haber en la sublista izquierda: " + leftValidation);
        System.out.println("Conteo que debería de haber en la sublista derecha: " + rightValidation);
        Integer lastElementLeftList = leftSubList.get(leftSubList.size() - 1);
        Integer firstElementRightList = rightSubList.get(0);


        if (lastElementLeftList + 1 != firstElementRightList){
            lostNumber = lastElementLeftList + 1;
        } else if (leftValidation > rightValidation || leftSubList.size() < rightSubList.size()){
            //El elemento faltante está en la parte izquierda porque la suma es más grande
            getLostElement(leftSubList, 1, leftSubList.size());
        } else if (leftValidation < rightValidation || leftSubList.size() > rightSubList.size()){
            //El elemento faltante está en la parte izquierda porque la suma es más grande
            getLostElement(rightSubList, 1, rightSubList.size());
        }
    }
}
