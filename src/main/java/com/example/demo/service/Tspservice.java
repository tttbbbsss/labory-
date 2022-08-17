package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class Tspservice {
//    @Autowired
//    结果
    public List<Integer[]> result;
//    得到数组下一个点位
    public int getnextpoint(Integer point){
        int beginpoint=point;
        if(beginpoint<0){
            return -1;
        }else {
            for (int i = 0; i < bestGh.length - 1; i++) {
                if(bestGh[i] == beginpoint) {
                    return bestGh[i + 1];
                }
                if(beginpoint==bestGh[bestGh.length - 1]){
                    return bestGh[0];
                }
            }
        }
        return -1;
    }

    public List<Integer[]> getResult() {
        return result;
    }

    public void setResult(List<Integer[]> result) {
        this.result = result;
    }

    public  final int MAX_GEN = 3000;//最大的迭代次数(提高这个值可以稳定地提高解质量，但是会增加求解时间)
    public  final int N = 100;//每次搜索领域的个数(这个值不要太大，太大的话搜索效率会降低)
    public  final int len = 20;//禁忌长度
    public  int cityNum ;//城市数量，手动设置

    public int getBestT() {
        return bestT;
    }

    public void setBestT(int bestT) {
        this.bestT = bestT;
    }

    public  int[][] tabooList;//禁忌表
    public  int[] Ghh;//初始路径编码

    public int[] getBestGh() {
        return bestGh;
    }

    public void setBestGh(int[] bestGh) {
        this.bestGh = bestGh;
    }

    public  int[] bestGh;//最好的路径编码
    public  int[] LocalGh;//当前最好路径编码
    public  int[] tempGh;//存放临时编码
    public  double[][] dist;//距离矩阵

    public double getBestEvaluation() {
        return bestEvaluation;
    }

    public void setBestEvaluation(double bestEvaluation) {
        this.bestEvaluation = bestEvaluation;
    }

    public  int bestT;//最佳的迭代次数
    public  double bestEvaluation;//最优解
    public  double LocalEvaluation;//每次领域搜索的最优解（领域最优解）
    public  double tempEvaluation;//临时解
    public List<Integer[]> pointList = new ArrayList<>();//每个城市的坐标
    public  int t;//当前迭代
    public Random random;//随机函数对象
    public int l = 0;//当前禁忌表长度


    //外部调用接口
    public void Run(List<Integer[]> pointListin) throws Exception {
        pointList=pointListin;
//        pointList.add(new Integer[]{6734,1453});
//        pointList.add(new Integer[]{2233,10});
//        pointList.add(new Integer[]{5530,1424});
//        pointList.add(new Integer[]{401,841});
//        pointList.add(new Integer[]{3082,1644});
//        pointList.add(new Integer[]{7608,4458});
//        pointList.add(new Integer[]{7573,3716});
//        pointList.add(new Integer[]{7265,1268});

//        long startTime = System.currentTimeMillis();
        initVar();
        TabuSearch();
//        System.out.println("bestT:"+bestT);
//        long endTime = System.currentTimeMillis();
//        System.out.println("求解用时："+(endTime-startTime)/1000.0+"秒");
    }

//    public

    //禁忌搜索主函数
    public void TabuSearch(){
        //开始迭代，停止条件为达到指定迭代次数
        while (t<=MAX_GEN){
            //当前领域搜索次数
            int n = 0;
            LocalEvaluation = Double.MAX_VALUE;
            while (n <= N){
                //得到当前编码Ghh的邻居编码tempGh
                tempGh = generateNewGh(Ghh.clone(),tempGh.clone());
                //判断其是否在禁忌表中
                if(!judge(tempGh)){
                    //如果不在
                    tempEvaluation = evaluate(tempGh);
                    if(tempEvaluation < LocalEvaluation){
                        //如果临时解优于本次领域搜索的最优解
                        //那么就将临时解替换本次领域搜索的最优解
                        LocalGh = tempGh.clone();
                        LocalEvaluation = tempEvaluation;
                    }
                    n++;
                }
            }
            if(LocalEvaluation < bestEvaluation){
                //如果本次搜索的最优解优于全局最优解
                //那么领域最优解替换全局最优解
                bestT = t;
                bestGh = LocalGh.clone();
                bestEvaluation = evaluate(bestGh);
            }
            Ghh = LocalGh.clone();
            //加入禁忌表
            enterTabooList(LocalGh.clone());
            t++;
            //System.out.println("第"+t+"代：bestEvaluation = "+bestEvaluation);
        }
        //求解完毕
        System.out.println("最佳迭代次数:"+bestT);
        System.out.println("最佳长度为:"+bestEvaluation);
        System.out.println("最佳路径为:");
        for (int i = 0; i < bestGh.length; i++) {
            System.out.print(bestGh[i]+"-->");
//            result.add(new Integer[]{bestGh[i],bestGh[i+1]});
        }
        System.out.println(bestGh[0]);
    }
    //加入禁忌队列
    public void enterTabooList(int[] tempGh){
        if(l<len){
            //如果当前禁忌表还有空位，则直接加入即可
            tabooList[l] = tempGh.clone();
            l++;
        }else{
            //如果禁忌表已经满了，则移除第一个进表的路径，添加新的路径到禁忌表末尾
            //后面的禁忌编码全部向前移动一位，覆盖掉当前第一个禁忌编码
            for (int i = 0; i < tabooList.length-1; i++) {
                tabooList[i] = tabooList[i+1].clone();
            }
            //将tempGh加入到禁忌队列的最后
            tabooList[tabooList.length-1] = tempGh.clone();
        }
    }
    //判断路径编码是否存在于禁忌表中
    public boolean judge(int[] tempGh){
        int count = 0;
        for (int i = 0; i < tabooList.length; i++) {
            for (int j = 0; j < tabooList[i].length; j++) {
                if(tempGh[j]!=tabooList[i][j]){
                    count++;
                    break;
                }
            }
        }
        return count!=tabooList.length;
    }
    //领域交换，生成新解(随机指定数组中的两个数，不包括首尾，然后让这两个数进行位置互换，达到生成一个新路线的作用)
    public int[] generateNewGh(int[]Gh,int[]tempGh) {
        int temp;
        //将Gh复制到tempGh
        tempGh = Gh.clone();

        int r1 = 0;
        int r2 = 0;
        ////这段代码(r1==0||r2==0)是为了保证起点不受改变，如果有固定的起点的话，可以使用这几行代码
        while (r1==r2||(r1==0||r2==0)){
            r1 = random.nextInt(cityNum);
            r2 = random.nextInt(cityNum);
        }
        while (r1==r2){
            r1 = random.nextInt(cityNum);
            r2 = random.nextInt(cityNum);
        }
        //交换
        temp=tempGh[r1];
        tempGh[r1]=tempGh[r2];
        tempGh[r2]=temp;
        return tempGh.clone();
    }
    //生成一个初始解
    public int[] getInitGh() throws Exception {
        //固定起点为地点数组的第一个元素
        Ghh[0] = 0;
        //记录已生成的点
        List<Integer> indexList = new ArrayList<>();
        indexList.add(0);
        //随机生成其余点
        for (int i = 1; i < Ghh.length; i++) {
            while (true){
                int r = random.nextInt(cityNum);
                if(!indexList.contains(r)){
                    //只有当地点不重合时才添加进列表，保证初始解中没有重复的地点
                    indexList.add(r);
                    Ghh[i] = r;
                    break;
                }
            }
        }
        System.out.println("初始解："+ Arrays.toString(Ghh));
        return Ghh.clone();
    }
    //计算两点之间的距离（使用伪欧氏距离，可以减少计算量）
    public double getDistance(Integer[] place1,Integer[] place2){
        //伪欧氏距离在根号内除以了一个10
        return Math.sqrt((Math.pow(place1[0]-place2[0],2)+Math.pow(place1[1]-place2[1],2))/10.0);
        //return Math.sqrt((Math.pow(place1[0]-place2[0],2)+Math.pow(place1[1]-place2[1],2)));
    }
    //初始化变量
    public void initVar() throws Exception {
        cityNum = pointList.size();//城市数量为点的数量
        tabooList = new int[len][cityNum];//禁忌表
        Ghh = new int[cityNum];//初始路径编码
        bestGh = new int[cityNum];//最好的路径编码
        LocalGh = new int[cityNum];//当前最好路径编码
        tempGh = new int[cityNum];//存放临时编码
        dist = new double[cityNum][cityNum];//距离矩阵
        //初始化距离矩阵
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                if(i==j){
                    //对角线为0
                    dist[i][j] = 0.0;
                }else{
                    //计算i到j的距离
                    dist[i][j] = getDistance(pointList.get(i),pointList.get(j));
                }
            }
        }
        //初始化参数
        bestT = 0;
        t = 0;
        random = new Random(520);
        Ghh = getInitGh();

        //复制当前路径编码给最优路径编码
        tempGh = Ghh.clone();
        bestGh = tempGh.clone();
        bestEvaluation = evaluate(bestGh);
        tempEvaluation = evaluate(tempGh);
        LocalEvaluation = Double.MAX_VALUE;
    }
    //评价函数
    public double evaluate(int[] path){
        double pathLen = 0.0;
        for (int i = 1; i < path.length; i++) {
            //起点到终点途径路程累加
            pathLen += dist[path[i-1]][path[i]];
        }
        //然后再加上返回起点的路程
        pathLen += dist[path[0]][path[path.length-1]];
        return pathLen;
    }
}
