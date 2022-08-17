package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class Kmeansservice {
    //        定义要聚类的数量
    int number_1;

    public int getNumber_1() {
        return number_1;
    }

    public void setNumber_1(int number_1) {
        this.number_1 = number_1;
    }

    //    外部执行函数
    public ArrayList<float[]> Run(ArrayList<float[]> dataSet,Integer numberclu) throws Exception {
        ArrayList<float[]> center;
        Kmeans k=new Kmeans(numberclu);
        //设置原始数据集
        k.setDataSet(dataSet);
        //执行算法
        k.execute();
        center=k.getCenter();
        //得到聚类结果
        ArrayList<ArrayList<float[]>> cluster=k.getCluster();
        //查看结果
        for(int i=0;i<cluster.size();i++)
        {
            k.printDataArray(cluster.get(i), "cluster["+i+"]");
        }
        return center;
    }

    /**
     * K均值聚类算法
     */
    public class Kmeans {
        public int numOfCluster;// 分成多少簇
        public int timeOfIteration;// 迭代次数
        public int dataSetLength;// 数据集元素个数，即数据集的长度
        public ArrayList<float[]> dataSet;// 数据集链表

        public ArrayList<float[]> getCenter() {
            return center;
        }

        public void setCenter(ArrayList<float[]> center) {
            this.center = center;
        }

        public ArrayList<float[]> center;// 中心链表
        public ArrayList<ArrayList<float[]>> cluster; //簇
        public ArrayList<Float> sumOfErrorSquare;// 误差平方和
        public Random random;

        /**
         * 设置需分组的原始数据集
         *
         * @param dataSet
         */

        public void setDataSet(ArrayList<float[]> dataSet) {
            this.dataSet = dataSet;
        }

        /**
         * 获取结果分组
         *
         * @return 结果集
         */

        public ArrayList<ArrayList<float[]>> getCluster() {
            return cluster;
        }

        /**
         * 构造函数，传入需要分成的簇数量
         *
         * @param numOfCluster
         *    簇数量,若numOfCluster<=0时，设置为1，若numOfCluster大于数据源的长度时，置为数据源的长度
         */
        public Kmeans(int numOfCluster) {
            if (numOfCluster <= 0) {
                numOfCluster = 1;
            }
            this.numOfCluster = numOfCluster;
        }

        /**
         * 初始化
         */
        public void init() {
            timeOfIteration = 0;
            random = new Random();
            //如果调用者未初始化数据集，则采用内部测试数据集
            if (dataSet == null || dataSet.size() == 0) {
                initDataSet();
            }
            dataSetLength = dataSet.size();
            //若numOfCluster大于数据源的长度时，置为数据源的长度
            if (numOfCluster > dataSetLength) {
                numOfCluster = dataSetLength;
            }
            center = initCenters();
            cluster = initCluster();
            sumOfErrorSquare = new ArrayList<Float>();
        }

        /**
         * 如果调用者未初始化数据集，则采用内部测试数据集
         */
        public void initDataSet() {
            dataSet = new ArrayList<float[]>();
            // 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
            float[][] dataSetArray = new float[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
                    { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
                    { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 } };

            for (int i = 0; i < dataSetArray.length; i++) {
                dataSet.add(dataSetArray[i]);
            }
        }

        /**
         * 初始化中心数据链表，分成多少簇就有多少个中心点
         *
         * @return 中心点集
         */
        public ArrayList<float[]> initCenters() {
            ArrayList<float[]> center = new ArrayList<float[]>();
            int[] randoms = new int[numOfCluster];
            boolean flag;
            int temp = random.nextInt(dataSetLength);
            randoms[0] = temp;
            //randoms数组中存放数据集的不同的下标
            for (int i = 1; i < numOfCluster; i++) {
                flag = true;
                while (flag) {
                    temp = random.nextInt(dataSetLength);

                    int j=0;
                    for(j=0; j<i; j++){
                        if(randoms[j] == temp){
                            break;
                        }
                    }

                    if (j == i) {
                        flag = false;
                    }
                }
                randoms[i] = temp;
            }

            // 测试随机数生成情况
            // for(int i=0;i<numOfCluster;i++)
            // {
            // System.out.println("test1:randoms["+i+"]="+randoms[i]);
            // }

            // System.out.println();

            for (int i = 0; i < numOfCluster; i++) {
                center.add(dataSet.get(randoms[i]));// 生成初始化中心链表
            }
//        System.out.println(center);
            return center;
        }

        /**
         * 初始化簇集合
         *
         * @return 一个分为k簇的空数据的簇集合
         */
        public ArrayList<ArrayList<float[]>> initCluster() {
            ArrayList<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
            for (int i = 0; i < numOfCluster; i++) {
                cluster.add(new ArrayList<float[]>());
            }

            return cluster;
        }

        /**
         * 计算两个点之间的距离
         *
         * @param element
         *            点1
         * @param center
         *            点2
         * @return 距离
         */
        public float distance(float[] element, float[] center) {
            float distance = 0.0f;
            float x = element[0] - center[0];
            float y = element[1] - center[1];
            float z = x * x + y * y;
            distance = (float) Math.sqrt(z);

            return distance;
        }

        /**
         * 获取距离集合中最小距离的位置
         *
         * @param distance
         *            距离数组
         * @return 最小距离在距离数组中的位置
         */
        public int minDistance(float[] distance) {
            float minDistance = distance[0];
            int minLocation = 0;
            for (int i = 1; i < distance.length; i++) {
                if (distance[i] <= minDistance) {
                    minDistance = distance[i];
                    minLocation = i;
                }
            }
            return minLocation;
        }

        /**
         * 核心，将当前元素放到最小距离中心相关的簇中
         */
        public void clusterSet() {
            float[] distance = new float[numOfCluster];
            for (int i = 0; i < dataSetLength; i++) {
                for (int j = 0; j < numOfCluster; j++) {
                    distance[j] = distance(dataSet.get(i), center.get(j));
                    // System.out.println("test2:"+"dataSet["+i+"],center["+j+"],distance="+distance[j]);
                }
                int minLocation = minDistance(distance);
                // System.out.println("test3:"+"dataSet["+i+"],minLocation="+minLocation);
                // System.out.println();

                cluster.get(minLocation).add(dataSet.get(i));// 核心，将当前元素放到最小距离中心相关的簇中

            }
        }

        /**
         * 求两点误差平方的方法
         *
         * @param element
         *            点1
         * @param center
         *            点2
         * @return 误差平方
         */
        public float errorSquare(float[] element, float[] center) {
            float x = element[0] - center[0];
            float y = element[1] - center[1];

            float errSquare = x * x + y * y;

            return errSquare;
        }

        /**
         * 计算误差平方和准则函数方法
         */
        public void countRule() {
            float jcF = 0;
            for (int i = 0; i < cluster.size(); i++) {
                for (int j = 0; j < cluster.get(i).size(); j++) {
                    jcF += errorSquare(cluster.get(i).get(j), center.get(i));

                }
            }
            sumOfErrorSquare.add(jcF);
        }

        /**
         * 设置新的簇中心方法
         */
        public void setNewCenter() {
            for (int i = 0; i < numOfCluster; i++) {
                int n = cluster.get(i).size();
                if (n != 0) {
                    float[] newCenter = { 0, 0 };
                    for (int j = 0; j < n; j++) {
                        newCenter[0] += cluster.get(i).get(j)[0];
                        newCenter[1] += cluster.get(i).get(j)[1];
                    }
                    // 设置一个平均值
                    newCenter[0] = newCenter[0] / n;
                    newCenter[1] = newCenter[1] / n;
                    center.set(i, newCenter);
                }
            }
        }

        /**
         * 打印数据，测试用
         *
         * @param dataArray
         *            数据集
         * @param dataArrayName
         *            数据集名称
         */
        public void printDataArray(ArrayList<float[]> dataArray,
                                   String dataArrayName) {
            for (int i = 0; i < dataArray.size(); i++) {
                System.out.println("print:" + dataArrayName + "[" + i + "]={"
                        + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
            }
            System.out.println("===================================");
        }

        /**
         * Kmeans算法核心过程方法
         */
        public void kmeans() {
            init();
            printDataArray(dataSet,"initDataSet");
            printDataArray(center,"initCenter");

            // 循环分组，直到误差不变为止
            while (true) {
                clusterSet();
                // for(int i=0;i<cluster.size();i++)
                // {
                // printDataArray(cluster.get(i),"cluster["+i+"]");
                // }

                countRule();

                // System.out.println("count:"+"sumOfErrorSquare["+timeOfIteration+"]="+sumOfErrorSquare.get(timeOfIteration));

                // System.out.println();
                // 误差不变了，分组完成
                if (timeOfIteration != 0) {
                    if (sumOfErrorSquare.get(timeOfIteration) - sumOfErrorSquare.get(timeOfIteration - 1) == 0) {
                        break;
                    }
                }

                setNewCenter();
                printDataArray(center,"newCenter");
                timeOfIteration++;
                cluster.clear();
                cluster = initCluster();
            }

            // System.out.println("note:the times of repeat:timeOfIteration="+timeOfIteration);//输出迭代次数
        }

        /**
         * 执行算法
         */
        public void execute() {
            long startTime = System.currentTimeMillis();
            System.out.println("kmeans begins");
            kmeans();
            long endTime = System.currentTimeMillis();
            System.out.println("kmeans running time=" + (endTime - startTime)
                    + "ms");
            System.out.println("kmeans ends");
            System.out.println();
        }
    }
}
