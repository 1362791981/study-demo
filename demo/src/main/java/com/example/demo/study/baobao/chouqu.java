package com.example.demo.study.baobao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class chouqu {

    public static void main(String[] args) {

        //1.看每个学校下都有什么学科，按照学校和学科的调查对象数（同一学校的同一学科调查对象数相同）从小到大排序。
        List<Sch_Sub> sch_sub_map = new ArrayList<Sch_Sub>();//此时已经按照大小排好序了，map中key为单位和学科，value为总条数
        System.out.println("############################第一种情况##################################");
        Sch_Sub sch_sub0 = new Sch_Sub();
        sch_sub0.setSch_sub("10001_1001");
        sch_sub0.setSum(4);
        sch_sub_map.add(sch_sub0);

        Sch_Sub sch_sub2 = new Sch_Sub();
        sch_sub2.setSch_sub("10002_1002");
        sch_sub2.setSum(1);
        sch_sub_map.add(sch_sub2);
        Sch_Sub sch_sub3 = new Sch_Sub();
        sch_sub3.setSch_sub("10003_1003");
        sch_sub3.setSum(1);
        sch_sub_map.add(sch_sub3);
        Sch_Sub sch_sub4 = new Sch_Sub();
        sch_sub4.setSch_sub("10004_1004");
        sch_sub4.setSum(1);
        sch_sub_map.add(sch_sub4);
        System.out.println("sch_sub_map = " + sch_sub_map.toString());
        execute(sch_sub_map,4);
        System.out.println();


        System.out.println("############################第二种情况##################################");
        sch_sub_map = new ArrayList<>();
        Sch_Sub sch_sub00 = new Sch_Sub();
        sch_sub00.setSch_sub("10001_1001");
        sch_sub00.setSum(1);
        sch_sub_map.add(sch_sub00);

        Sch_Sub sch_sub222 = new Sch_Sub();
        sch_sub222.setSch_sub("10002_1002");
        sch_sub222.setSum(1);
        sch_sub_map.add(sch_sub222);
        Sch_Sub sch_sub333 = new Sch_Sub();
        sch_sub333.setSch_sub("10003_1003");
        sch_sub333.setSum(1);
        sch_sub_map.add(sch_sub333);
        Sch_Sub sch_sub444 = new Sch_Sub();
        sch_sub444.setSch_sub("10004_1004");
        sch_sub444.setSum(1);
        sch_sub_map.add(sch_sub444);
        Sch_Sub sch_sub555 = new Sch_Sub();
        sch_sub555.setSch_sub("10005_1005");
        sch_sub555.setSum(1);
        sch_sub_map.add(sch_sub555);
        System.out.println("sch_sub_map = " + sch_sub_map.toString());
        execute(sch_sub_map,5);
        System.out.println();

        System.out.println("############################第三种情况##################################");
        sch_sub_map = new ArrayList<>();
        Sch_Sub sch_sub0000 = new Sch_Sub();
        sch_sub0000.setSch_sub("10001_1001");
        sch_sub0000.setSum(1);
        sch_sub_map.add(sch_sub0000);

        Sch_Sub sch_sub2222 = new Sch_Sub();
        sch_sub2222.setSch_sub("10002_1002");
        sch_sub2222.setSum(1);
        sch_sub_map.add(sch_sub2222);
        Sch_Sub sch_sub3333 = new Sch_Sub();
        sch_sub3333.setSch_sub("10003_1003");
        sch_sub3333.setSum(1);
        sch_sub_map.add(sch_sub3333);
        Sch_Sub sch_sub4444 = new Sch_Sub();
        sch_sub4444.setSch_sub("10004_1004");
        sch_sub4444.setSum(1);
        sch_sub_map.add(sch_sub4444);
        Sch_Sub sch_sub5555 = new Sch_Sub();
        sch_sub5555.setSch_sub("10005_1005");
        sch_sub5555.setSum(1);
        sch_sub_map.add(sch_sub5555);
        Sch_Sub sch_sub6666 = new Sch_Sub();
        sch_sub6666.setSch_sub("10006_1006");
        sch_sub6666.setSum(1);
        sch_sub_map.add(sch_sub6666);
        System.out.println("sch_sub_map = " + sch_sub_map.toString());
        execute(sch_sub_map,6);
        System.out.println();

        System.out.println("############################第四种情况##################################");
        sch_sub_map = new ArrayList<>();
        Sch_Sub sch_sub00000= new Sch_Sub();
        sch_sub00000.setSch_sub("10001_1001");
        sch_sub00000.setSum(10);
        sch_sub_map.add(sch_sub00000);
        System.out.println("sch_sub_map = " + sch_sub_map.toString());
        execute(sch_sub_map,1);
        System.out.println();


        System.out.println("############################第五种情况##################################");
        sch_sub_map = new ArrayList<>();
        Sch_Sub sch_sub000000= new Sch_Sub();
        sch_sub000000.setSch_sub("10001_1001");
        sch_sub000000.setSum(5);
        sch_sub_map.add(sch_sub000000);
        Sch_Sub sch_sub111111= new Sch_Sub();
        sch_sub111111.setSch_sub("10001_1002");
        sch_sub111111.setSum(5);
        sch_sub_map.add(sch_sub111111);
        System.out.println("sch_sub_map = " + sch_sub_map.toString());
        execute(sch_sub_map,2);
        System.out.println();
    }

    public static void execute(List<Sch_Sub> sch_sub_map,int school_subject_sum) {

        //1.看每个学校下都有什么学科，按照学校和学科的调查对象数（同一学校的同一学科调查对象数相同）从小到大排序。
        //List<Sch_Sub> sch_sub_map = new ArrayList<Sch_Sub>();//此时已经按照大小排好序了，map中key为单位和学科，value为总条数

        Map saveMap = new HashMap();
        //保留数save = 5
        int save = 5;

        //2.如果当前学校数小于保留数，证明单个的学校+学科拥有的保留数 至少等于1，且可以大于1
        if (school_subject_sum < save) {
            //3.每个学校都至少要保留一条
            //使用递归计算各个学校+学科应有的保留数
            int liveSave = save;
            for (int i = 0; i < sch_sub_map.size(); i++) {
                Map map = getSave(liveSave, school_subject_sum, sch_sub_map, i);
                if(map.get("liveSave") == null || (Integer)map.get("liveSave") == 0) break;
                liveSave = (Integer) map.get("liveSave");
                saveMap.putAll(map);
            }
        } else {
            //学校数大于保留数，有部分学校无法获得，即 去掉多余学校，其余学校每个学校一个保留数
            for (int i = 0; i < sch_sub_map.size() - (school_subject_sum - save); i++) {
                saveMap.put(sch_sub_map.get(i).getSch_sub(), 1);
            }
        }
        //最后得出saveMap,key为每个学校+学科，vule为应该保留条数
        System.out.println(saveMap);
    }

    /**
     * 计算每个学校+学科应有的保留条数
     *
     * @param save
     * @param school_subject_sum
     * @param sch_sub_map
     * @return
     */
    private static Map getSave(int save, int school_subject_sum, List<Sch_Sub> sch_sub_map, int i) {
        Map<String, Integer> sch_sub_save = new HashMap<>();
        if (i >= sch_sub_map.size() || save <= 0) {
            return sch_sub_save;
        }
        Sch_Sub sch_sub = sch_sub_map.get(i);

        int maxSave = 0;
        //3.1先判断第 i 组（同学校下同学科）总条数sum<保留数，那么最多可以保留sum条。
        if (sch_sub.getSum() < save) {
            maxSave = sch_sub.getSum();
            //3.1.1此时判断school_subject_sum-1<save-sum,即剩余学校够分
            if (school_subject_sum - 1 < save - maxSave) {
                //继续
                sch_sub_save.put(sch_sub.getSch_sub(), maxSave);
                //剩余学校继续分剩余的保留数(递归调用下一批次的，学校+学科)
                sch_sub_save.putAll(getSave(save - maxSave, school_subject_sum, sch_sub_map, i + 1));
            } else {
                //3.1.2如果剩余学校不够分，那么其余学校都只能有一条保留数，当前学校保留数为 保留数save-剩余学校数(school_subject_sum - (i + 1))
                maxSave = save - (school_subject_sum - i - 1);
                sch_sub_save.put(sch_sub.getSch_sub(), maxSave);
            }
        } else {
            //3.2如果当前学校+学科拥有的总条数>保留数,如果学校数大于1.那么其他学校至少分到一条
            maxSave = save - school_subject_sum + 1;
            sch_sub_save.put(sch_sub.getSch_sub(), maxSave);

        }
        sch_sub_save.put("liveSave",save - maxSave);
        return sch_sub_save;
    }
}

@Data
class Sch_Sub {
    private String sch_sub;

    private Integer sum;
}