package com.software_engineering.tap.ExplorePage;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class popGraph 
{
    public void popGraph()
    {
        //empty constructor    
    }   
    public LineGraphSeries<DataPoint> populateBTC()
    {
        double btchistory[] = new double[]{ 6926.02,
                6816.74,7049.79,7417.89,6789.30,6774.75,6620.41,6896.28,7022.71,
                6773.94,6830.90,6939.55,7916.37,7889.23,8003.68,8357.04,7890.15,
                8163.69,8373.74,8863.50,8917.60,8792.63,8938.30,9652.16,8864.09,
                9279.00,8978.33,9342.47,9392.03,9314.39};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,btchistory[0]), new DataPoint(1,btchistory[1]),
                new DataPoint(2,btchistory[2]), new DataPoint(3,btchistory[3]),
                new DataPoint(4,btchistory[4]), new DataPoint(5,btchistory[5]),
                new DataPoint(6,btchistory[6]), new DataPoint(7,btchistory[7]),
                new DataPoint(8,btchistory[8]), new DataPoint(9,btchistory[9]),
                new DataPoint(10,btchistory[10]), new DataPoint(11,btchistory[11]),
                new DataPoint(12,btchistory[12]), new DataPoint(13,btchistory[13]),
                new DataPoint(14,btchistory[14]), new DataPoint(15,btchistory[15]),
                new DataPoint(16,btchistory[16]), new DataPoint(17,btchistory[17]),
                new DataPoint(18,btchistory[18]), new DataPoint(19,btchistory[19]),
                new DataPoint(20,btchistory[20]), new DataPoint(21,btchistory[21]),
                new DataPoint(22,btchistory[22]), new DataPoint(23,btchistory[23]),
                new DataPoint(24,btchistory[24]), new DataPoint(25,btchistory[25]),
                new DataPoint(26,btchistory[26]), new DataPoint(27,btchistory[27]),
                new DataPoint(28,btchistory[28]), new DataPoint(29,btchistory[29])
        });
        
        return series;
    }
    public LineGraphSeries<DataPoint> populateLTC()
    {
        double ltchistory[] = new double[]{116.02,
                120.96,111.34,120.18,126.74,117.69,119.34,113.79,119.38,117.40,
                114.97,114.17,114.96,120.62,130.82,125.43,130.58,126.23,136.72,
                136.09,142.21,149.97,148.94,149.71,151.17,162.37,145.96,146.96,
                150.49,152.24};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,ltchistory[0]), new DataPoint(1,ltchistory[1]),
                new DataPoint(2,ltchistory[2]), new DataPoint(3,ltchistory[3]),
                new DataPoint(4,ltchistory[4]), new DataPoint(5,ltchistory[5]),
                new DataPoint(6,ltchistory[6]), new DataPoint(7,ltchistory[7]),
                new DataPoint(8,ltchistory[8]), new DataPoint(9,ltchistory[9]),
                new DataPoint(10,ltchistory[10]), new DataPoint(11,ltchistory[11]),
                new DataPoint(12,ltchistory[12]), new DataPoint(13,ltchistory[13]),
                new DataPoint(14,ltchistory[14]), new DataPoint(15,ltchistory[15]),
                new DataPoint(16,ltchistory[16]), new DataPoint(17,ltchistory[17]),
                new DataPoint(18,ltchistory[18]), new DataPoint(19,ltchistory[19]),
                new DataPoint(20,ltchistory[20]), new DataPoint(21,ltchistory[21]),
                new DataPoint(22,ltchistory[22]), new DataPoint(23,ltchistory[23]),
                new DataPoint(24,ltchistory[24]), new DataPoint(25,ltchistory[25]),
                new DataPoint(26,ltchistory[26]), new DataPoint(27,ltchistory[27]),
                new DataPoint(28,ltchistory[28]), new DataPoint(29,ltchistory[29])
        });

        return series;
    }
    public LineGraphSeries<DataPoint> populateETH()
    {
        double ethhistory[] = new double[]{403.11,
                364.77,391.73,406.30,376.37,380.39,367.15,386.97,401.62,396.42,
                405.83,420.71,463.98,514.14,492.46,522.74,503.76,513.49,511.00,
                553.86,588.93,602.99,633.63,643.71,699.05,618.01,631.29,674.25,
                688.18,680.94};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,ethhistory[0]), new DataPoint(1,ethhistory[1]),
                new DataPoint(2,ethhistory[2]), new DataPoint(3,ethhistory[3]),
                new DataPoint(4,ethhistory[4]), new DataPoint(5,ethhistory[5]),
                new DataPoint(6,ethhistory[6]), new DataPoint(7,ethhistory[7]),
                new DataPoint(8,ethhistory[8]), new DataPoint(9,ethhistory[9]),
                new DataPoint(10,ethhistory[10]), new DataPoint(11,ethhistory[11]),
                new DataPoint(12,ethhistory[12]), new DataPoint(13,ethhistory[13]),
                new DataPoint(14,ethhistory[14]), new DataPoint(15,ethhistory[15]),
                new DataPoint(16,ethhistory[16]), new DataPoint(17,ethhistory[17]),
                new DataPoint(18,ethhistory[18]), new DataPoint(19,ethhistory[19]),
                new DataPoint(20,ethhistory[20]), new DataPoint(21,ethhistory[21]),
                new DataPoint(22,ethhistory[22]), new DataPoint(23,ethhistory[23]),
                new DataPoint(24,ethhistory[24]), new DataPoint(25,ethhistory[25]),
                new DataPoint(26,ethhistory[26]), new DataPoint(27,ethhistory[27]),
                new DataPoint(28,ethhistory[28]), new DataPoint(29,ethhistory[29])
        });

        return series;
    }
    public LineGraphSeries<DataPoint> populateBCH()
    {
        double bchhistory[] = new double[]{708.98,
                656.34,669.68,711.16,652.89,630.16,607.08,650.54,653.51,639.36,
                648.35,655.83,703.67,763.08,730.88,772.52,751.58,777.17,847.74,
                975.67,1099.27,1149.86,1233.71,1356.53,1484.87,1295.2,1329.80,
                1386.36,1403.51,1435.03,1390.13};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,bchhistory[0]), new DataPoint(1,bchhistory[1]),
                new DataPoint(2,bchhistory[2]), new DataPoint(3,bchhistory[3]),
                new DataPoint(4,bchhistory[4]), new DataPoint(5,bchhistory[5]),
                new DataPoint(6,bchhistory[6]), new DataPoint(7,bchhistory[7]),
                new DataPoint(8,bchhistory[8]), new DataPoint(9,bchhistory[9]),
                new DataPoint(10,bchhistory[10]), new DataPoint(11,bchhistory[11]),
                new DataPoint(12,bchhistory[12]), new DataPoint(13,bchhistory[13]),
                new DataPoint(14,bchhistory[14]), new DataPoint(15,bchhistory[15]),
                new DataPoint(16,bchhistory[16]), new DataPoint(17,bchhistory[17]),
                new DataPoint(18,bchhistory[18]), new DataPoint(19,bchhistory[19]),
                new DataPoint(20,bchhistory[20]), new DataPoint(21,bchhistory[21]),
                new DataPoint(22,bchhistory[22]), new DataPoint(23,bchhistory[23]),
                new DataPoint(24,bchhistory[24]), new DataPoint(25,bchhistory[25]),
                new DataPoint(26,bchhistory[26]), new DataPoint(27,bchhistory[27]),
                new DataPoint(28,bchhistory[28]), new DataPoint(29,bchhistory[29])
        });
        

        return series;
    }
    public LineGraphSeries<DataPoint> populateEUR()
    {
        double eurhistory[] = new double[]{1.2148,1.2107,1.2165,1.2234,1.2210,1.2313,1.348,1.2378,
                1.2372,1.2382, 1.2348,1.2327,1.2368,1.2354,1.2322,1.2299,1.2240,1.2282,1.2271,2.2304,
                1.2331,1.2302,1.2311,1.2408,1.2449,1.2357,1.2322,1.2343,1.2247,1.2336,1.2288};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,eurhistory[0]), new DataPoint(1,eurhistory[1]),
                new DataPoint(2,eurhistory[2]), new DataPoint(3,eurhistory[3]),
                new DataPoint(4,eurhistory[4]), new DataPoint(5,eurhistory[5]),
                new DataPoint(6,eurhistory[6]), new DataPoint(7,eurhistory[7]),
                new DataPoint(8,eurhistory[8]), new DataPoint(9,eurhistory[9]),
                new DataPoint(10,eurhistory[10]), new DataPoint(11,eurhistory[11]),
                new DataPoint(12,eurhistory[12]), new DataPoint(13,eurhistory[13]),
                new DataPoint(14,eurhistory[14]), new DataPoint(15,eurhistory[15]),
                new DataPoint(16,eurhistory[16]), new DataPoint(17,eurhistory[17]),
                new DataPoint(18,eurhistory[18]), new DataPoint(19,eurhistory[19]),
                new DataPoint(20,eurhistory[20]), new DataPoint(21,eurhistory[21]),
                new DataPoint(22,eurhistory[22]), new DataPoint(23,eurhistory[23]),
                new DataPoint(24,eurhistory[24]), new DataPoint(25,eurhistory[25]),
                new DataPoint(26,eurhistory[26]), new DataPoint(27,eurhistory[27]),
                new DataPoint(28,eurhistory[28]), new DataPoint(29,eurhistory[29])
        });


        return series;
    }
    public LineGraphSeries<DataPoint> populateGBP()
    {
        double gbphistory[] = new double[]{1.3776,1.3780,1.3916,1.3932,1.3979,1.3939,1.4006,1.4004,
                1.4086,1.4203,1.4288,1.4339,1.4244,1.4242,1.4232,1.4178,1.4177,1.4129,1.4089,1.4087,
                1.4005,1.4082,1.4060,1.4050,1.4031,1.4016,1.4031,1.4080,1.4162,1.4227};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,gbphistory[0]), new DataPoint(1,gbphistory[1]),
                new DataPoint(2,gbphistory[2]), new DataPoint(3,gbphistory[3]),
                new DataPoint(4,gbphistory[4]), new DataPoint(5,gbphistory[5]),
                new DataPoint(6,gbphistory[6]), new DataPoint(7,gbphistory[7]),
                new DataPoint(8,gbphistory[8]), new DataPoint(9,gbphistory[9]),
                new DataPoint(10,gbphistory[10]), new DataPoint(11,gbphistory[11]),
                new DataPoint(12,gbphistory[12]), new DataPoint(13,gbphistory[13]),
                new DataPoint(14,gbphistory[14]), new DataPoint(15,gbphistory[15]),
                new DataPoint(16,gbphistory[16]), new DataPoint(17,gbphistory[17]),
                new DataPoint(18,gbphistory[18]), new DataPoint(19,gbphistory[19]),
                new DataPoint(20,gbphistory[20]), new DataPoint(21,gbphistory[21]),
                new DataPoint(22,gbphistory[22]), new DataPoint(23,gbphistory[23]),
                new DataPoint(24,gbphistory[24]), new DataPoint(25,gbphistory[25]),
                new DataPoint(26,gbphistory[26]), new DataPoint(27,gbphistory[27]),
                new DataPoint(28,gbphistory[28]), new DataPoint(29,gbphistory[29])
        });


        return series;
    }
    public LineGraphSeries<DataPoint> populateJPY()
    {
        double jpyhistory[] = new double[]{.0092,.0092,.0092,.0092,.0091,.0092,.0093,.0093,.0093,
                .0093,.0093,.0093,.0093,.0093,.0093,.0093,.0093,.0094,.0093,.0094,.0094,.0094,.0094,.0093,
                .0094,.0094,.0095,.0094,.0094,.0094};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,jpyhistory[0]), new DataPoint(1,jpyhistory[1]),
                new DataPoint(2,jpyhistory[2]), new DataPoint(3,jpyhistory[3]),
                new DataPoint(4,jpyhistory[4]), new DataPoint(5,jpyhistory[5]),
                new DataPoint(6,jpyhistory[6]), new DataPoint(7,jpyhistory[7]),
                new DataPoint(8,jpyhistory[8]), new DataPoint(9,jpyhistory[9]),
                new DataPoint(10,jpyhistory[10]), new DataPoint(11,jpyhistory[11]),
                new DataPoint(12,jpyhistory[12]), new DataPoint(13,jpyhistory[13]),
                new DataPoint(14,jpyhistory[14]), new DataPoint(15,jpyhistory[15]),
                new DataPoint(16,jpyhistory[16]), new DataPoint(17,jpyhistory[17]),
                new DataPoint(18,jpyhistory[18]), new DataPoint(19,jpyhistory[19]),
                new DataPoint(20,jpyhistory[20]), new DataPoint(21,jpyhistory[21]),
                new DataPoint(22,jpyhistory[22]), new DataPoint(23,jpyhistory[23]),
                new DataPoint(24,jpyhistory[24]), new DataPoint(25,jpyhistory[25]),
                new DataPoint(26,jpyhistory[26]), new DataPoint(27,jpyhistory[27]),
                new DataPoint(28,jpyhistory[28]), new DataPoint(29,jpyhistory[29])
        });


        return series;
    }
    public LineGraphSeries<DataPoint> populateCNY()
    {
        double cnyhistory[] = new double[]{6.3330,6.3330,6.3599,6.3259,6.3063,6.3171,6.2965,6.2965,
                6.2791,6.2744,6.2820,6.2946,6.2744,6.2820,6.2946,6.2749,6.2749,6.2747,6.2909,6.2686,
                6.819,6.3100,6.3020,6.3020,6.3020,6.3060,6.3060,6.2866,6.2866,6.2866,6.2892,6.2975};
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0,cnyhistory[0]), new DataPoint(1,cnyhistory[1]),
                new DataPoint(2,cnyhistory[2]), new DataPoint(3,cnyhistory[3]),
                new DataPoint(4,cnyhistory[4]), new DataPoint(5,cnyhistory[5]),
                new DataPoint(6,cnyhistory[6]), new DataPoint(7,cnyhistory[7]),
                new DataPoint(8,cnyhistory[8]), new DataPoint(9,cnyhistory[9]),
                new DataPoint(10,cnyhistory[10]), new DataPoint(11,cnyhistory[11]),
                new DataPoint(12,cnyhistory[12]), new DataPoint(13,cnyhistory[13]),
                new DataPoint(14,cnyhistory[14]), new DataPoint(15,cnyhistory[15]),
                new DataPoint(16,cnyhistory[16]), new DataPoint(17,cnyhistory[17]),
                new DataPoint(18,cnyhistory[18]), new DataPoint(19,cnyhistory[19]),
                new DataPoint(20,cnyhistory[20]), new DataPoint(21,cnyhistory[21]),
                new DataPoint(22,cnyhistory[22]), new DataPoint(23,cnyhistory[23]),
                new DataPoint(24,cnyhistory[24]), new DataPoint(25,cnyhistory[25]),
                new DataPoint(26,cnyhistory[26]), new DataPoint(27,cnyhistory[27]),
                new DataPoint(28,cnyhistory[28]), new DataPoint(29,cnyhistory[29])
        });


        return series;
    }
    
}
