package com.mechanist.gravityphysicssimulator.utils;

import com.mechanist.gravityphysicssimulator.Graphics.Material;
import com.mechanist.gravityphysicssimulator.Graphics.Mesh;
import com.mechanist.gravityphysicssimulator.Graphics.Vertex;
import com.mechanist.gravityphysicssimulator.Math.Vector2f;
import com.mechanist.gravityphysicssimulator.Math.Vector3f;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeshLoader {

    public static Mesh fileToMesh(String path) {
        List<Vertex> vertices = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        Material mat;
        try {
            List<String> list = Files.readAllLines(new File(path).toPath());

            for (int x = 0; x < list.size(); x++) {
                String l = list.get(x);
                String r = l.replace("[", "");
                String r1 = r.replace("]", "");
                ArrayList<String> l1 = new ArrayList<String>(Arrays.asList(r1.split(",")));

                if (list.size() - 2 > x) {
                    vertices.add(new Vertex(new Vector3f(Float.parseFloat(l1.get(0)), Float.parseFloat(l1.get(1)), Float.parseFloat(l1.get(2))), new Vector3f(Float.parseFloat(l1.get(3)), Float.parseFloat(l1.get(4)), Float.parseFloat(l1.get(5))), new Vector2f(Float.parseFloat(l1.get(6)), Float.parseFloat(l1.get(7)))));

                } else if (list.size() - x == 2) {
                    for (int y = 0; y < l1.size(); y++) {
                        intList.add(Integer.parseInt(l1.get(y)));
                    }
                } else if (list.size() - x == 1) {
                    return new Mesh(vertices.toArray(new Vertex[0]), intList.stream().mapToInt(i -> i).toArray(), new Material(r1));
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int x = 0; x < intList.size(); x++) {

        }


//        Mesh mesh = new Mesh(new Vertex( new Vector3f(list.get(0).indexOf(0), list.get(0).indexOf(1), list.get(0).indexOf(2)), new Vector3f(list.get(0).indexOf(3), list.get(0).indexOf(4), list.get(0).indexOf(5)), new Vector2f(list.get(0).indexOf(6), list.get(0).indexOf(7))));
        return null;
    }
}
