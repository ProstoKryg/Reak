import java.util.Scanner;

public class Reaktor
{
    Scanner sc = new Scanner(System.in);
    int temp=40;
    int	level=10;
    int reaktor=3;

    int	max_level=10;
    int	min_level=0;

    int max_temp=100;
    int min_temp=0;
    int vod = 0;

    boolean	stopped=false;
    /**
     *	Поток, повышающий температуру реактора реактора
     */
    Thread heigh=new Thread()
    {	public void run()
    { while(!stopped)
    {	try
    {	sleep(1000);
        temp= temp + reaktor;
        reaktor++;
        System.out.println("температура повышена до "+temp);
    }
    catch(Exception pe)
    {
        System.out.println("Error heigh: " + pe);
    }
    }
    }
    };
    /**
     *	Поток, опускающий стержень в реактор
     */
    Thread low=new Thread()
    {	public void run()
    { while(!stopped)
    {	try
    {	sleep(1000);
        level--;
        temp= temp - level;
        System.out.println("стержень отпушен до "+level);
        System.out.println("температура понижена до "+temp);

    }
    catch(Exception pe)
    {
        System.out.println("Error low: " + pe);
    }
    }
    }
    };

    /**
     *	Поток, проверяющий состояне температуры реактора
     */
    Thread control=new Thread()
    {	public void run()
    { while(!stopped)
    {	try
    {	sleep(1);

        if(temp>=max_temp)
        {	System.out.println("Реактор взорвался... уровень "+level);
            stopped=true;
            System.exit(0);
        }
        if(level<=min_level)
        {	System.out.println("Реактор заглох... уровень "+level);
            stopped=true;
            System.exit(0);
        }
        if(temp<=min_temp)
        {	System.out.println("Реактор заглох... уровень "+level);
            stopped=true;
            System.exit(0);
        }
    }
    catch(Exception pe)
    {
        System.out.println("Error : " + pe);
    }
    }
    }
    };
/**
 *	Поток, оператора
 */
Thread operator=new Thread()
{
    public void run()
{
    while(!stopped)
{
    try
{
    sleep(1);
    vod=0;
    System.out.println("Оператор выберете параметер который хотите изменить: 1 temp 2 level 3 reaktor 4 max_level 5 min_level 6 max_temp 7 min_temp 8 взорвать реактор 9 остоновить реактор");
    vod= sc.nextInt();
    System.out.println("Ваш выбор:");
    if (vod == 1){
        System.out.println("на кокой пареметр изменить?:" );
        temp=sc.nextInt();
    }

    if (vod == 2){
        System.out.println("на кокой пареметр изменить?:");
        level=sc.nextInt();
    }
    if (vod == 3){
        System.out.println("на кокой пареметр изменить?:");
        reaktor=sc.nextInt();
    }
    if (vod == 4){
        System.out.println("на кокой пареметр изменить?:");
        max_level=sc.nextInt();
    }
    if (vod == 5){
        System.out.println("на кокой пареметр изменить?:");
        min_level=sc.nextInt();
    }
    if (vod == 6){
        System.out.println("на кокой пареметр изменить?:");
        max_temp=sc.nextInt();
    }
    if (vod == 7){
        System.out.println("на кокой пареметр изменить?:");
        min_temp=sc.nextInt();
    }
    if (vod == 8){
        System.out.println("Реактор взорвался... уровень "+level);
        stopped=true;
        System.exit(0);
    }
    if (vod == 9){
        System.out.println("Реактор заглох... уровень "+level);
        stopped=true;
        System.exit(0);
    }
}
catch(Exception pe)
{
    System.out.println("Error : " + pe);
}
}
}
};


    /**
     *
     */
    public Reaktor()
    {
        control.setPriority(Thread.NORM_PRIORITY);	//NORM_PRIORITY MIN_PRIORITY
        low.setPriority(Thread.NORM_PRIORITY);
        heigh.setPriority(Thread.NORM_PRIORITY);
        operator.setPriority(Thread.NORM_PRIORITY);

        control.start();
        heigh.start();
        low.start();
        operator.start();
    }
    /**
     *
     */
    public static void main(String args[])
    {	System.out.println("Реактор запущен");
        new Reaktor();
    }

}
