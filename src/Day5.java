import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {

    private static List<Mapping> seedToSoilMapping;
    private static List<Mapping> soilToFertilizerMapping;
    private static List<Mapping> fertilizerToWaterMapping;
    private static List<Mapping> waterToLightMapping;
    private static List<Mapping> lightToTemperatureMapping;
    private static List<Mapping> temperatureToHumidityMapping;
    private static List<Mapping> humidityToLocationMapping;

    public static void main(String[] args) {
        initRealCase();

        //        seedToSoilMapping = List.of(
        //                new Mapping(50L, 98L, 2L),
        //                new Mapping(52L, 50L, 48L)
        //        );
        //
        //        soilToFertilizerMapping = List.of(
        //        new Mapping(0L, 15L, 37L),
        //        new Mapping(37L, 52L, 2L),
        //        new Mapping(39L, 0L, 15L)
        //        );
        //
        //        fertilizerToWaterMapping = List.of(
        //                new Mapping(49L, 53L, 8L),
        //        new Mapping(0L, 11L, 42L),
        //        new Mapping(42L, 0L, 7L),
        //        new Mapping(57L, 7L, 4L)
        //        );
        //
        //        waterToLightMapping = List.of(
        //                new Mapping(88L, 18L, 7L),
        //        new Mapping(18L, 25L, 70L)
        //        );
        //
        //        lightToTemperatureMapping = List.of(
        //                new Mapping(45L, 77L, 23L),
        //        new Mapping(81L, 45L, 19L),
        //        new Mapping(68L, 64L, 13L)
        //        );
        //
        //        temperatureToHumidityMapping = List.of(
        //                new Mapping(0L, 69L, 1L),
        //                new Mapping(1L, 0L, 69L)
        //        );
        //
        //        humidityToLocationMapping = List.of(
        //                new Mapping(60L, 56L, 37L),
        //                new Mapping(56L, 93L, 4L)
        //        );


        List<SeedPair> seeds = List.of(
//                new SeedPair(91926764L,235794528L),              802908268
                new SeedPair(3279509610L,325625103L),//103047735
                new SeedPair(2781720183L,218217413L),//103047735
                new SeedPair(1315129829L,102999617L),//103047735
                new SeedPair(3995609239L,143268116L),//103047735
                new SeedPair(358337926L,185836835L), //57451709
                new SeedPair(1543999077L,241888600L),//57451709
                new SeedPair(1795811745L,806228439L),
                new SeedPair(2616560939L,56204204L),
                new SeedPair(869828854L,224520829L));
        //        List<Long> seeds = List.of(79L, 14L, 55L, 13L);
        System.out.println(minValue(seeds));

    }

    private static void initRealCase() {
        seedToSoilMapping = List.of(
                new Mapping(2076625497L,3385713231L,258448094L),
                new Mapping(933162806L,1124446801L,128749435L),
                new Mapping(744984268L,625015359L,188178538L),
                new Mapping(551924411L,211563684L,96582495L),
                new Mapping(2664738738L,2072311374L,297142807L),
                new Mapping(1406760174L,813193897L,14102104L),
                new Mapping(0L,1274151473L,331435427L),
                new Mapping(403777493L,976299883L,148146918L),
                new Mapping(1987639580L,2783535044L,88985917L),
                new Mapping(4074594902L,3332858447L,52854784L),
                new Mapping(1484055780L,86124597L,125439087L),
                new Mapping(331435427L,371339681L,72342066L),
                new Mapping(1146768491L,1605586900L,152911849L),
                new Mapping(3283022369L,2992105862L,70998045L),
                new Mapping(648506906L,528537997L,96477362L),
                new Mapping(1609494867L,827296001L,149003882L),
                new Mapping(3685297224L,3111036616L,121638972L),
                new Mapping(1420862278L,308146179L,63193502L),
                new Mapping(2961881545L,3644161325L,321140824L),
                new Mapping(1061912241L,443681747L,56957522L),
                new Mapping(4127449686L,2872520961L,119584901L),
                new Mapping(3806936196L,2497318144L,139794743L),
                new Mapping(3585114365L,3232675588L,100182859L),
                new Mapping(1118869763L,500639269L,27898728L),
                new Mapping(2335073591L,3965302149L,329665147L),
                new Mapping(3984585308L,2407308550L,90009594L),
                new Mapping(1320635577L,0L,86124597L),
                new Mapping(1841217423L,2637112887L,146422157L),
                new Mapping(4247034587L,3063103907L,47932709L),
                new Mapping(1299680340L,1253196236L,20955237L),
                new Mapping(3354020414L,1841217423L,231093951L),
                new Mapping(3946730939L,2369454181L,37854369L));

        soilToFertilizerMapping = List.of(
                new Mapping(3384134166L, 1995234330L, 176305237L),
                new Mapping(1155226403L, 855999534L, 411447090L),
                new Mapping(2208613005L, 3459130785L, 737289315L),
                new Mapping(3115190977L, 2188772380L, 56379264L),
                new Mapping(3046023331L, 2245151644L, 69167646L),
                new Mapping(3287350250L, 3362346869L, 96783916L),
                new Mapping(0L, 688893976L, 167105558L),
                new Mapping(199962926L, 1300303992L, 576627555L),
                new Mapping(1566673493L, 0L, 380943110L),
                new Mapping(1995234330L, 2314319290L, 213378675L),
                new Mapping(776590481L, 380943110L, 307950866L),
                new Mapping(3560439403L, 2527697965L, 734527893L),
                new Mapping(2945902320L, 3262225858L, 100121011L),
                new Mapping(1084541347L, 1876931547L, 70685056L),
                new Mapping(3229639031L, 4196420100L, 40478406L),
                new Mapping(3270117437L, 2171539567L, 17232813L),
                new Mapping(167105558L, 1267446624L, 32857368L),
                new Mapping(3171570241L, 4236898506L, 58068790L)
        );

        fertilizerToWaterMapping = List.of(
                new Mapping(2261570026L, 3454758517L, 88568015L),
                new Mapping(1802864872L, 1796719466L, 196521844L),
                new Mapping(1029796290L, 924285105L, 94936250L),
                new Mapping(1502956048L, 1019221355L, 34573577L),
                new Mapping(1675836952L, 4234138393L, 33837263L),
                new Mapping(383249040L, 0L, 273576862L),
                new Mapping(1124732540L, 457794332L, 53985776L),
                new Mapping(2350138041L, 2091507351L, 48912742L),
                new Mapping(1674136750L, 1795019264L, 1700202L),
                new Mapping(3859927050L, 2781536522L, 163983633L),
                new Mapping(90982234L, 273576862L, 129799649L),
                new Mapping(1537529625L, 511780108L, 260859L),
                new Mapping(2488789087L, 1993241310L, 98266041L),
                new Mapping(2196492990L, 3543326532L, 49319261L),
                new Mapping(2254009624L, 2694827028L, 7560402L),
                new Mapping(2587055128L, 3111811340L, 69748037L),
                new Mapping(2002799677L, 4168500219L, 65638174L),
                new Mapping(840643879L, 512040967L, 108011574L),
                new Mapping(1390364751L, 688875858L, 8559931L),
                new Mapping(1398924682L, 721632529L, 104031366L),
                new Mapping(656825902L, 620052541L, 68823317L),
                new Mapping(1709674215L, 3189756750L, 73094246L),
                new Mapping(2068437851L, 1671843595L, 123175669L),
                new Mapping(73501861L, 906804732L, 17480373L),
                new Mapping(2656803165L, 3853597757L, 225164158L),
                new Mapping(3592079857L, 3612742204L, 240855553L),
                new Mapping(2191613520L, 2945520155L, 4879470L),
                new Mapping(2881967323L, 2950399625L, 161411715L),
                new Mapping(0L, 1085334645L, 69517503L),
                new Mapping(725649219L, 697435789L, 24196740L),
                new Mapping(2245812251L, 3181559377L, 8197373L),
                new Mapping(220781883L, 1375323327L, 162467157L),
                new Mapping(3270779999L, 2140420093L, 321299858L),
                new Mapping(2399050783L, 4078761915L, 89738304L),
                new Mapping(4023910683L, 2702387430L, 79149092L),
                new Mapping(69517503L, 1053794932L, 3984358L),
                new Mapping(1782768461L, 3592645793L, 20096411L),
                new Mapping(1308391575L, 1057779290L, 27555355L),
                new Mapping(749845959L, 1154852148L, 61727890L),
                new Mapping(1671843595L, 2692533873L, 2293155L),
                new Mapping(1335946930L, 403376511L, 54417821L),
                new Mapping(3832935410L, 4267975656L, 26991640L),
                new Mapping(3043379038L, 2461719951L, 227400961L),
                new Mapping(948655453L, 825663895L, 81140837L),
                new Mapping(4103059775L, 3262850996L, 191907521L),
                new Mapping(1999386716L, 2689120912L, 3412961L),
                new Mapping(1178718316L, 1216580038L, 129673259L),
                new Mapping(811573849L, 1346253297L, 29070030L)
        );

        waterToLightMapping = List.of(
                new Mapping(12536522L, 803922375L, 381092756L),
                new Mapping(1034093555L, 2274122448L, 375903462L),
                new Mapping(4046347183L, 3727145496L, 30218294L),
                new Mapping(1644177017L, 1468743601L, 210972620L),
                new Mapping(2033158562L, 0L, 65899273L),
                new Mapping(3918690677L, 4044943223L, 48984895L),
                new Mapping(3252369528L, 2650025910L, 46811980L),
                new Mapping(3846317697L, 4159436488L, 72372980L),
                new Mapping(825337225L, 701230416L, 92775221L),
                new Mapping(708443948L, 3110054761L, 116893277L),
                new Mapping(4281152040L, 4231809468L, 13815256L),
                new Mapping(4076565477L, 3945094008L, 99849215L),
                new Mapping(467053271L, 262914333L, 56912139L),
                new Mapping(457136533L, 794005637L, 9916738L),
                new Mapping(4176414692L, 3757363790L, 104737348L),
                new Mapping(1960807084L, 319826472L, 72351478L),
                new Mapping(3983208893L, 4096298198L, 63138290L),
                new Mapping(2099057835L, 2696837890L, 179036871L),
                new Mapping(3400708401L, 4093928118L, 2370080L),
                new Mapping(393629278L, 2210615193L, 63507255L),
                new Mapping(2278094706L, 1723463860L, 487151333L),
                new Mapping(3351365829L, 4245624724L, 49342572L),
                new Mapping(523965410L, 65899273L, 184478538L),
                new Mapping(1855149637L, 595572969L, 105657447L),
                new Mapping(2765246039L, 1185015131L, 283728470L),
                new Mapping(961860085L, 3226948038L, 72233470L),
                new Mapping(3967675572L, 3711612175L, 15533321L),
                new Mapping(3486071351L, 3351365829L, 360246346L),
                new Mapping(3403078481L, 3862101138L, 82992870L),
                new Mapping(918112446L, 1679716221L, 43747639L),
                new Mapping(1409997017L, 2875874761L, 234180000L),
                new Mapping(0L, 250377811L, 12536522L),
                new Mapping(3048974509L, 392177950L, 203395019L)
        );

        lightToTemperatureMapping = List.of(
                new Mapping(3663115998L, 2273256818L, 16619810L),
                new Mapping(1779396221L, 3140211126L, 35421643L),
                new Mapping(1671064478L, 2289876628L, 5675989L),
                new Mapping(2459496143L, 2164474153L, 65282060L),
                new Mapping(1676740467L, 3228344832L, 68171740L),
                new Mapping(3108268812L, 3396614149L, 34367621L),
                new Mapping(0L, 1247024027L, 117995309L),
                new Mapping(3142636433L, 2302123739L, 520479565L),
                new Mapping(3914906966L, 3296516572L, 48028032L),
                new Mapping(1451315146L, 20271696L, 29756177L),
                new Mapping(3962934998L, 1685507004L, 235533336L),
                new Mapping(3679735808L, 3872604190L, 235171158L),
                new Mapping(201522431L, 0L, 20271696L),
                new Mapping(2606764356L, 2257434699L, 15822119L),
                new Mapping(1095940165L, 930151315L, 235007724L),
                new Mapping(2092197835L, 2032400258L, 15603728L),
                new Mapping(1814817864L, 4107775348L, 187191948L),
                new Mapping(2107801563L, 3430981770L, 351694580L),
                new Mapping(221794127L, 50027873L, 874146038L),
                new Mapping(2524778203L, 2082488000L, 81986153L),
                new Mapping(4205039456L, 3811944039L, 60660151L),
                new Mapping(2622586475L, 1921040340L, 111359918L),
                new Mapping(3051554215L, 3344544604L, 52069545L),
                new Mapping(1605883623L, 1648004635L, 37502369L),
                new Mapping(3103623760L, 1643359583L, 4645052L),
                new Mapping(1412812877L, 1442569054L, 38502269L),
                new Mapping(2039485772L, 3175632769L, 52712063L),
                new Mapping(123972713L, 1365019336L, 77549718L),
                new Mapping(1744912207L, 2048003986L, 34484014L),
                new Mapping(4198468334L, 2295552617L, 6571122L),
                new Mapping(2733946393L, 2822603304L, 317607822L),
                new Mapping(117995309L, 924173911L, 5977404L),
                new Mapping(1330947889L, 1165159039L, 81864988L),
                new Mapping(2002009812L, 1605883623L, 37475960L),
                new Mapping(4265699607L, 3782676350L, 29267689L),
                new Mapping(1643385992L, 2229756213L, 27678486L)
        );

        temperatureToHumidityMapping = List.of(
                new Mapping(1358631653L, 987873070L, 147949589L),
                new Mapping(2413964705L, 2097965649L, 70341317L),
                new Mapping(816457527L, 972386794L, 15486276L),
                new Mapping(2080824412L, 2168306966L, 250363611L),
                new Mapping(958855341L, 2055074721L, 42890928L),
                new Mapping(1948315905L, 1968747613L, 86327108L),
                new Mapping(1810667112L, 2669632213L, 137648793L),
                new Mapping(2484306022L, 857156868L, 115229926L),
                new Mapping(1001746269L, 1523646763L, 356885384L),
                new Mapping(1641013697L, 687503453L, 169653415L),
                new Mapping(2698691839L, 0L, 56146061L),
                new Mapping(4138641394L, 2975929973L, 156325902L),
                new Mapping(831943803L, 2418670577L, 38696072L),
                new Mapping(3039761928L, 4068116224L, 226851072L),
                new Mapping(870639875L, 1880532147L, 88215466L),
                new Mapping(2975929973L, 3132255875L, 63831955L),
                new Mapping(3839900820L, 3497181952L, 298740574L),
                new Mapping(3567707122L, 3795922526L, 272193698L),
                new Mapping(3266613000L, 3203991608L, 293190344L),
                new Mapping(83162889L, 56146061L, 361318942L),
                new Mapping(1506581242L, 553070998L, 134432455L),
                new Mapping(2754837900L, 500627892L, 52443106L),
                new Mapping(2034643013L, 1135822659L, 46181399L),
                new Mapping(786124536L, 2556522540L, 30332991L),
                new Mapping(2599535948L, 2457366649L, 99155891L),
                new Mapping(3559803344L, 3196087830L, 7903778L),
                new Mapping(0L, 417465003L, 83162889L),
                new Mapping(444481831L, 1182004058L, 341642705L),
                new Mapping(2331188023L, 2586855531L, 82776682L)
        );

        humidityToLocationMapping = List.of(
                new Mapping(1050996268L, 1532072704L, 311260544L),
                new Mapping(3180071790L, 2724566227L, 289337188L),
                new Mapping(4221284312L, 3899164493L, 35309413L),
                new Mapping(3807278034L, 2656088631L, 16719113L),
                new Mapping(1362256812L, 2203016881L, 453071750L),
                new Mapping(338253566L, 1843333248L, 359683633L),
                new Mapping(697937199L, 1009396889L, 40111814L),
                new Mapping(2970507299L, 3589172780L, 195028044L),
                new Mapping(2338906915L, 3808103383L, 1357317L),
                new Mapping(1879134746L, 3052743004L, 5325923L),
                new Mapping(1815328562L, 346829408L, 63806184L),
                new Mapping(3899164493L, 3972847477L, 185700981L),
                new Mapping(2340264232L, 0L, 83356697L),
                new Mapping(2610350026L, 3013903415L, 38839589L),
                new Mapping(0L, 671143323L, 338253566L),
                new Mapping(2423620929L, 3402443683L, 186729097L),
                new Mapping(738049013L, 1049508703L, 312947255L),
                new Mapping(1884460669L, 561071833L, 110071490L),
                new Mapping(3714686766L, 254238140L, 92591268L),
                new Mapping(3469408978L, 1362455958L, 169616746L),
                new Mapping(2799625856L, 83356697L, 170881443L),
                new Mapping(3639025724L, 3784200824L, 23902559L),
                new Mapping(4084865474L, 4158548458L, 136418838L),
                new Mapping(1994532159L, 3058068927L, 344374756L),
                new Mapping(4256593725L, 3934473906L, 38373571L),
                new Mapping(3165535343L, 3809460700L, 14536447L),
                new Mapping(2649189615L, 410635592L, 150436241L),
                new Mapping(3662928283L, 2672807744L, 51758483L)
        );
    }

    public static Long transformVal(Long value, Mapping transformation) {
        if(value >= transformation.source && value < transformation.source + transformation.range) {
            return transformation.target + (value - transformation.source);
        } else {
            return value;
        }
    }

    public static Map<Long, Long> transformMappingToMap(List<Mapping> mapping) {
        Mapping sourceMapping = null;
        Map<Long, Long> transformedMap = new HashMap<>();
        Long highestVal;
        for (Mapping currentMapping : mapping) {
            if(sourceMapping == null) {
                sourceMapping = currentMapping;
                highestVal = Math.max(sourceMapping.source, sourceMapping.target) + sourceMapping.range;
                for (long i = 0; i < highestVal; i++) {
                    transformedMap.put(i, i);
                }
            }
            recordToRecord(transformedMap, currentMapping);


            sourceMapping = currentMapping;
        }

        return transformedMap;
    }

    public static Map<Long, Long> recordToRecord(Map<Long, Long> map, Mapping mapping) {
        for (int i = 0; i < mapping.range; i++) {
            map.put(mapping.source + i, mapping.target + i);
        }
        return map;
    }

    private static Long minValue(List<SeedPair> seedPair) {
        Long minLocation = null;
        int i = 0;
        for (SeedPair pair : seedPair) {
            System.out.println("Pair : " + ++i);
            for (int j = 0; j < pair.range; j++) {
                Long seed = pair.value + j;
                Long locationNumber = seedsToLocation(seed);
//                System.out.println(locationNumber);
                if(minLocation == null || minLocation > locationNumber) {
                    minLocation = locationNumber;
                }
            }
            System.gc();
            System.out.println(minLocation);
        }
        return minLocation;
    }

    private static Long seedsToLocation(Long seedNumber) {
//        System.out.println("Seed :" + seedNumber);
        Long soilNumber = seedToSoil(seedNumber);
//        System.out.println("Soil :" + soilNumber);
        Long fertilizerNumber = soilToFertilizer(soilNumber);
//        System.out.println("Fert :" + fertilizerNumber);
        Long waterNumber = fertilizerToWater(fertilizerNumber);
//        System.out.println("Wat :" + waterNumber);
        Long lightNumber = waterToLight(waterNumber);
//        System.out.println("Light :" + lightNumber);
        Long temperatureNumber = lightToTemperature(lightNumber);
//        System.out.println("Temp :" + temperatureNumber);
        Long humidityNumber = temperatureToHumidity(temperatureNumber);
//        System.out.println("Hum :" + humidityNumber);
        Long locationNumber = humidityToLocation(humidityNumber);
//        System.out.println("Loc :" + locationNumber);
        return locationNumber;
    }

    private static Long seedToSoil(Long seedNumber) {
        Long newVal = seedNumber;
        for (Mapping mapping : seedToSoilMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }
            newVal = newNewVal;
        }
        return newVal;
    }

    private static Long soilToFertilizer(Long soilNumber) {
        Long newVal = soilNumber;
        for (Mapping mapping : soilToFertilizerMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }
        }
        return newVal;
    }

    private static Long fertilizerToWater(Long fertilizerNumber) {
        Long newVal = fertilizerNumber;
        for (Mapping mapping : fertilizerToWaterMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }}
        return newVal;
    }

    private static Long waterToLight(Long waterNumber) {
        Long newVal = waterNumber;
        for (Mapping mapping : waterToLightMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }}
        return newVal;
    }

    private static Long lightToTemperature(Long lightNumber) {
        Long newVal = lightNumber;
        for (Mapping mapping : lightToTemperatureMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }}
        return newVal;
    }

    private static Long temperatureToHumidity(Long temperatureNumber) {
        Long newVal = temperatureNumber;
        for (Mapping mapping : temperatureToHumidityMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }}
        return newVal;
    }

    private static Long humidityToLocation(Long humidityNumber) {
        Long newVal = humidityNumber;
        for (Mapping mapping : humidityToLocationMapping) {
            Long newNewVal = transformVal(newVal, mapping);
            if(!newNewVal.equals(newVal)) {
                return newNewVal;
            }}
        return newVal;
    }

    public record SeedPair(Long value, Long range) {
    }

    public record Mapping(Long target, Long source, Long range) {}
}
