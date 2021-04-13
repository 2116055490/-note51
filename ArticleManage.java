package edu.xcdq;

import java.util.Scanner;

/**
 * @author xujinwei
 * @date 2021/4/7 9:52
 */
public class ArticleManage {
    ArticleSet articleSet = new ArticleSet();
    
    //仓库初始化，放入一些商品
    public void initial(){
        Article xiaomi11 = new Article();
        /*
        xiaomi11.name = "小米11";
        xiaomi11.number = 30;
        xiaomi11.amount = 0;
        xiaomi11.price = 1999;*/
        
        xiaomi11.setArticle("小米11",1030,0,0);
        Article xiaomi11pro = new Article();
        xiaomi11pro.setArticle("小米11pro",2999,10,0);
        Article xiaomiUltra = new Article();
        xiaomiUltra.setArticle("小米至尊版",3999,20,0);
        
        articleSet.articles[0] = xiaomi11;
        articleSet.articles[1] = xiaomi11pro;
        articleSet.articles[2] = xiaomiUltra;
        


    }
    
    //启动菜单
    public  void startMenu(){

        boolean flag = true;
        do{
            System.out.println("***********************");
            System.out.println("1.查看商品信息");
            System.out.println("2.新增商品信息");
            System.out.println("3.删除商品信息");
            System.out.println("4.卖出商品");
            System.out.println("5.商品销售排行榜");
            System.out.println("6.退出");
            System.out.println("***********************");

            System.out.println("请输入功能编号:");
            Scanner scanner = new Scanner(System.in);
            int funNo = scanner.nextInt();
            switch (funNo){
                case 1:
                    System.out.println("1.查看商品信息");
                    chakan();
                    break;
                case 2:
                    System.out.println("2.新增商品信息");
                    add();
                    break;
                case 3:
                    System.out.println("3.删除商品信息");
                    delete();
                    break;
                case 4:
                    System.out.println("4.卖出商品");
                    saleOut();
                    break;
                case 5:
                    System.out.println("5.商品销售排行榜");
                    leadeBand();
                    break;
                case 6:
                    System.out.println("谢谢，已经退出");
                    flag = false;
                    break;
                default:
                    System.out.println("您输入的功能编号有误");
                    break;
            }
        }while(flag);
    }

    public void chakan(){
        System.out.println("编号 \t 名字 \t 库存 \t 已售");
        for(int i=0;i<articleSet.articles.length;i++){
            if(articleSet.articles[i]!=null){
                articleSet.articles[i].print(i);
            }
        }
    }
    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入商品名称:");
        String name = scanner.next();
        System.out.println("请输入单价:");
        double price = scanner.nextDouble();
        System.out.println("请输入库存:");
        int count = scanner.nextInt();
        System.out.println("请输入已卖的数量:");
        int number = scanner.nextInt();

        Article newArticle = new Article();
        newArticle.setArticle(name,price,count,number);
        for(int i=0;i<articleSet.articles.length;i++){
            if(articleSet.articles[i] == null){
                articleSet.articles[i] = newArticle; //把新建的对象放在数组中的第一个空位置
                break; //后续的空位置直接跳过
            }
        }
    }
    private void delete() {
        System.out.println("请输入要删除的商品编号");
        Scanner scanner = new Scanner(System.in);
        int bianhao = scanner.nextInt();
        boolean flag = true;
        for(int i=0;i<articleSet.articles.length;i++){
            if(articleSet.articles[i] != null && (i+1)==bianhao){
                int j = i;
                //后面元素向前移动
                while(articleSet.articles[j+1] !=null){
                    articleSet.articles[j] = articleSet.articles[j+1];
                    j++;
                }
                articleSet.articles[j] = null;
                flag = true;
                break;  //后续的空数组元素没有必要执行
            }else{
                flag = false;
            }
        }
        if(flag){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
    private void saleOut() {   //卖出商品
        System.out.println("请输入你要卖出的商品");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        for (int i=0;i<articleSet.articles.length;i++){
            if ((articleSet.articles[i].name).equals(name)){
                System.out.println("请输入你要售出的数量");
                int shoumai = scanner.nextInt();
                if (shoumai<articleSet.articles[i].amount){
                    articleSet.articles[i].number=articleSet.articles[i].number+shoumai;//已卖数量
                    articleSet.articles[i].amount=articleSet.articles[i].amount-shoumai;//库存
                }else{
                    System.out.println("商品数量不足");

                }

                System.out.println("售卖成功");
                break;
            }
        }
    }
    private void leadeBand() {
        for(int i=0;i<articleSet.articles.length -1;i++){
            for(int j =0;j<articleSet.articles.length -i -1;j++){
                if (articleSet.articles[j] != null && articleSet.articles[j+i] != null){
                    if (articleSet.articles[j].number < articleSet.articles[j+i].number){
                        Article tempArticle = articleSet.articles[j];
                        articleSet.articles[j] = articleSet.articles[j+i];
                        articleSet.articles[j+1] = tempArticle;
                    }
                }
            }
        }
    }








}
