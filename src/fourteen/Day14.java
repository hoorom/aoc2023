package fourteen;

import java.util.List;

public class Day14 {

    public static String test = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....""";

    public static String input = """
            O.........O.......O...OO.O...#.#.O.#O...O.#O..O.##.##.O...#..O..#..O..##O#.#........O#.O..#..#......
            .OO..#OO.#.#.OO..#...O.OO.#......#.#OO..O##...##..#O#..#O##O.#....#O#O##.#.#O#.....O.#.O.#....O.....
            ..O.#.O.O#...#.#....#.#.....OO....#..O#....#.......O..OO..#..#.#O.O.OO...O.O...##...O.#...#.O.....O.
            ..O.O...#O....O#...O..#OO#....O.......O...#O##.O#....#O.#O.#.#.#...O#.O....O....##O#O#..O..#...#.O.O
            ##.O#.O..OOO..#....#.#..#..O.O.#.O.OO...##.O.O...#..##.O....O#..##.O.O....OO.....#OOO........O..#.OO
            #..OO.#.....OO.........OOO.O..OOO.#O#..#.#O..O#..#OO.O...........O.O.O.......###.....#.#..O...#....#
            #.O.O........#....O#..#OO...O..#.#.O..O.......#.O##.O.O........O..O.O.O.O#O.O#.....O.#.....#O.O.#O..
            .O..O..O.O.O..#O.....O...O....#O...#...#O...OO..OO...#O..#.##.#O...O..##O...##...O#..#.....#.#...#..
            OO..O#..#OOOO...##..#....O.#........#..O...#O......O..O..........#..O..##....OO....O.......#O...O.O.
            .#..O.........#......OOO.O...#...O.O..#..OO.#..O..#....##....O.....O#.#O.O.OO...O..#...O....O.......
            ..#..O.#O.....#...OOO.#.........O.O.#.O...#OO.O...#O..#O..#..#O...#...O......O#......O..#O..O##.....
            #.......#O...#O#O...O#O.O.O..............O.........O.O....#O##OO.O#..OO.....#.O..#.O.#.....OOO.#..#.
            O..OOOO.......#O.O..O.#..#...O......#..#...#......OO....O..O.O.#....O.O...O..#...##.O.....#.OO..##..
            .....#O.....O......#OO.#.O.#..#O.#..............#.O.....#.O.O..OO.#.#.#..O##.O.#O..O...O#...#.......
            OOO.OOO#..##........#.#OO..OO#.#..O...O.#.O...##O#.OOO#OO.....#.O..#.O..O......##.#..#.#OO#...#..#..
            ......#O...O....O......O...#O..O.#...O....O.O...##.O.#....#O..O..............O#..............#.OO.O.
            .#....##..O....O.O.#....O...O..O..O..#...#O.O..OOO.....O..#...#........O..#.OOO..O.#..#O.#.O........
            O......O...#..O#.........#O..#..........O..#.O.O#.#.####.OO.#.O.....OO.#..#OO.O......##..##.....#.#.
            #....##.O...#..##O...O......#OO.#OO.........OO#.O.O....O#....O#..O#...O...O#OO..O....O.#.O#.#..O.O..
            ......O.#...#O.......#..#...#.OOO..O.##..#.OO..OO...OO#O..O...OO.................#...O....#..OO...#O
            ..##....#O....O..#O..O..OO.O.O....O#.##..#O..O.O.O..#.OO#..O.....O.O...#OO.....#..#O.#...O#.........
            ..OO.O.OO.#.O..........#.O.O.#....#.......#.......OOO..#.....#..OO........#..O##O.....#...#O..O.....
            .OO#.O#O...O#.O#..#OO.#......#.O.....O..#O...O......O.OO###....OO#.O.........##.#..O.....#..........
            .O....O.O..O.....#.#...........#O.O.O#.............O....#....OO....#.....#OO..OO..OOO....O.O..#.OO.#
            ..O#.O....#..##...#...O....OO#O.#..O.O#..O..O..#..#....#....#..O..##..O.....#...O...#...#....#.OOO#O
            .O..O..O...#....O.OO.##..OO#O.#.....#.O..O#O.OO...#.##O..OO.O......##..O.O..#..OO#O#.O#O....O#.....O
            .OO...O##......O....##...O..##O##..O..OO##.#...O.##.OO.#.##....#.OO.....O..##.#.#..##O..O..O#..#O.O.
            O.#.O...#....O.####OOOO.OO.O.O#..##......O#O....#OO.O.#.....O.....O.##....#....#...O#..O.OOO#O......
            ##..O.........O.....OO....O#O.O..#..#O..#..OO...OOOO..OO.O.....O##..##.#O............#O....O.O..O...
            O.#..O.....#..#.#...O...O#.....O...#.OO...O.....O#.O.....O#O....##....#...O..#O...##..O...O...#..O..
            .O##O.#.O..O..#..O...O#....O....O....#..O.....O##OOO.....O###O#.O.O.O.O.#.OO...O......##.#O.....#..O
            .#OO.O#.#.#O...OO.OO....#OO.O#O..O..#..O..#.O.O.O..O#O...........O......OOO....#.O......#OO#..#OO#O.
            ...O#..#....#.O......#..O...OOO........O.#.#.....O.##......#....#...OO....O.O..O....O#.#.O#O.O....##
            .....##..OO#..O#.O.#..O....OO.....##.OO..O...O#...###..O#O.O...O.#O#..O.O.....O..O......O......O#..#
            O#..O#O...O.OO.OO....O###.#......#O....#O....O#...#O...O.....O#...........OO..O...O......#.#.#.O..#.
            ........#...#...O.O.#..OO..#O..O..O....OO...#....OO..###...#...#.#....O.OOO..#..O..###.O....O#OO..O.
            .#O#O......O.....O#..O.O...O......O#..OO.#.#.O.O.O..O#.#........O.......#..#.O....#.....##........#.
            O#O.O...#O.O#O.....#.....#####OOO..O#..#..#.#......O......#....#.....O.O..........#..##..O.........O
            ..#.O..OO......O.....O.OO..#.....O..##..#....O........#..O.#.......O...#OO#....##..O.#....O.O.#.O..O
            .#...O..#OO.#.O...OO.O.##...OOOO....#..O..............O.###.#.O...#.##OOO#O..O....O...O#.###.....O.O
            #O.#.O.#.O.....#...O#.O....##.........#..#.....O.....#...O..O.#O.O..O##..OOOO.......O.O#O.......O..O
            .#.O.......OO....OO#.OO.#O.O....##.#...##OO....OO...O.###..O.##O..O#...#...#O#...#.#O#.O.#..OO.##..O
            #.#.......O...O....#O#.#....#.O.#.....#......O...........#.O..#..O#.##...OO.#...O...#....O.......O..
            ....OO.O#O#..#O##O..#........#....O...OOO....##.#O..O...#..O.#O##..........O.#.O.....#O.OO.O#.....#.
            ..O.#O...##.....##..#.OO##.#...OOO.O.O......#.OO....O..#..#O...O.......O.....#O##..#....O#..O.##O.O.
            O...O....O....#..O............#O.OO..#..OOO.........OO#.#.O.O..O.OO....O......#.O#.#....O..#O......O
            #.......#...OOO.........#.#O.#O....O....O.O#...O#..O.O##O...O.#O..##O....OO........OO#.....#.#.O.#..
            .#......O#.O.O..OOO.......O#...#...OO#O.......O.....#O#.#.O..O.OO...OO##.#..O....O.#..##...O.#..#.O#
            .#..O......O.OO..#..#O.#..O#.O......#O..O.......##O.#...#....#..#.##O.#O...O....O.....O.#O...O#.O.#.
            OOO.#O#..........O#..O.....#...O.O.O..O.O....O...O#....O.O..OO...##.#.O#OO......#...#.O.......O#.O..
            ....#.#.......O.....##O....O...O....O..O#.O.....O#OO..#OO....#....#...O.......##......O.OO.......OO#
            .#........O.O..#.#...O..##.O.##..O.O.#....O..O#.#.#O.......O.......O.#.....O..#..#...#.O....OO.#.O..
            .#.O.#.....#....#.#.O.#O..O.OO#..#...OO.......#O#..#O.#......O#.O..#......#.O#.OO...#..O.......O.O..
            .#O##O#...O..........OO...OO..OO#...#OO..O....O..OOO...O.....##...O..#..###O.#O.###......O.OO#O#OO..
            .##....#........#..O#......OO.....OOO.......O.OO...#O...#.O#..O...OO.OO#.......#.#.#.O#..#O.#.#..O..
            #....#.##...O.OO..#.......O..O.O........O...O..OO.......#.O#O#.O.O.OO##.#.#...##.......#....O.O#....
            ...##....O#.#...O..#OO.O#.O...OO......O#.....#.##..O....#O.#....O..O.....OO#..#.O......#..#.##.#....
            O#O......#.#O.........O...OO.#.OO...##.#...OO....O..##.......OOO.O..#...O.O...OO.....#.O..#..#O.....
            .O.O#.........#.#...OOOOOO..#....#..O....OO...O..#....#..#.#O.#...O#..#.....O.##.O..#O#......O.O.O..
            .#.O#......O##....##.#OO.OO..#...#...O..O....#O..O.#......#..#...#..O.....##...#...O.O.##OOO##......
            ...OO#O...O....O......O#.##...#.........OOO....O.#O#......OO.#......OOO.#..........O#O....OO.#.O.#.O
            ....O..O.O...#.......OOO.#...#O.#.....O.#..#.O.#..O.....O#.##..O..#..#...#O.....OO.#O......#....O..O
            #....OO.O.O.OO#.O..#...#.#.##.O..OO...O#.OO..O#.#.O.#....O.O......O..##............O............O..O
            #O#.O.O.....OO......#..OO...#....O..#.O...#O.#....O.....O.#.O.O.O..#...OO.O......O...#..#..O...#..OO
            ..#..#.OO#..O.....#....#O#...#......O....#OO.O....#O.....#..#.O....####..#.O..#...OO..#..OO..##..#..
            ..O...OO....#.#.#....#.O...#.....O.........OO..#.#O.O..O..#O..O.....OO..#.#.#...O....#....OOO.OOO.#O
            .....##..O.O.O.O...#..OOO..........O.....##.#..O.#.....O......O.#....#...O....O..O.OOO.#O.....#..###
            #...#....O#.O...O.......#.#......#..O.#.........O....OOO.O.O..O..O##..O#O..#..O.#.....###....#.....O
            .#..O...#OO...O....O#O....#..###..#.O..##.O...O.....#O.OO......O#....#.....##.#..#......O.OO...##..O
            .O....#O#.#...O..O....O#..OO.#O....O..O..#.O#O.O....O####...O..O...#.##...O...#....###OO#.O#..#O#OOO
            O.......O#......O#OO...O..##.OO.O....O....#...O.#..OO#....OO..O##.O.OO..#.#O..#.O.#........#...O.#.#
            O...O.###O.#..#.#O..O#.##O#.O.O.#..#.....O...#OO..#.#....#....O...#.OOOO.#...#...O.O..O..O....#..OO.
            ....###.....OO.O...#...O..........#O.##.O.O.OO..O...O#........O.#.#OO#O.........OOO..............#..
            O.O.#.O##O..#OOOO.........O.O.O..#..O..O#.O....OO#...OO..#.O....OO.....OO..O..O.O#O#.O###OO..#OO.#.O
            OOO..O#O#O#.#O.O#..OO..#......###O....#..O..O....OO....O.###.OOO....O.......O.OO.#...O#O..O#O..#....
            ..OO......O##..####...#O#O#.O.#..#.....#..OO....#O...........OO...#.O....#O...#....OO.O...#..OO.OO.O
            #.....O..#.#..O..........#...#..O....O#........O#..O...O.##.#.O..O...O#O.O....#.....O..OO..#OO..#.#.
            O.O...........#.O.O.O.....#.#O#...OO......O.#...........OO..O.#O..#....O.O...#..#OO.O.O#..#..O#OOO#O
            .O#.#OO#..#..............OO.#.#........##..O...........O..#...##...OO..#O...#...OO.O.O.#..#..O...OO.
            .O.#.........O..........#O.O.......O...##O...OO#O.#O#.#O#..#......O#O..#O.##.O#.#.#..#O.......#..#..
            ..OOO....O#O.......O....O#....#.....O#...#..O.#.O.O.O.#...#.O.....#.........#.O..#..#.....O..O.O..#.
            ...OO..O...#........#....O.O#.O#O..O.......#O.......O##...O#.O.#.#O#.....OOO.OOOO.##....O..O..O#.O..
            ..O...O...O....O..#..OO....OO...#.#...O...#O..O..#.#.....OO.....OOOO......O.##.#..OO#..O#.....O#O.#O
            O.O.#O.#..##...O.OO...O.O...#.......#....O##..OO..#.O.O...O.O...##.......OO...#.O###.O...#.....O...O
            ..OO.....#O#OO##....O.###..##O.##.......O#O.#O..O.O#.O....O.#OO#...#..O.#.....#.O......O#O.O..O##O#.
            ..............O.O.#...OO.##.OO..OO....#...#..OOO.....#...#............O.O.......OO......OO.#O.#.....
            ........OO....O.##O.O#..##......O...OO..O#.O...O..........#.........#.....##...#.O#...O.O...........
            ..#.O...O.....O.OOO.O...##......#.##....#..#..#........#.O.O.O....O.#O.O#........#O..O.......O..O.#.
            ....OO#.....OO....#...O#..#.......#....O#...O..#..##.O..#..#.....#.#...OO..O..#...#..##.......O.O...
            .......#....##O.....OOOO....#.O....OOOO.#.OO.##..O...O#..#.###..#....#......O.O...O.#O#.........O.##
            ...##O...OO#....#OO...O..#O........#....#...#O...#..#.O...#.........#..O....O..O..O.O..#O...O..#.O#.
            ...O.O.......OO.......#O.......O...O...OO.O#O.O...O..#..#O......#O.........O.O...O.O....O#O.O.OO..O#
            #.#.#.#...#O.OO#..O..#O..#.....#...O.......O.#.O#.#....O.O...#OO.#......O.#..O...O.O...O..#.......#.
            ....#.#.....O........OO...#......#.O.O...##.OO..O#...#....O#........##...OO.#.....OO...#.#.O..#.#...
            ....OOO.#......##...O...O#.O..#.O.O...O..#.......OO.....O.#..OO..OOO#..O.#.#.O........OOO.#......#O#
            .....#O.O...OO#OOOO#..#...##....O...#.O..O..#...#..OOO.O#O...#..O.#..OO#.#O...O.O.O.O.O.OOO.O..O..#.
            ...#....#.....O#O...OO.#.#.OOO.O..#...##OO.OO....O.O..O...O...OO.O..O....#O.#..........OO..#.OOO....
            .#....#O#O..#O..O#.....#...#O#.....#O.O.#.##..#O.##.......O..#O.O..O........#.O#...#O##...##.......#
            ...#......O.O...OO#O..#....O....O...#..OOO.OO#..#...OOO.OO...O.#.#.#.O.O..O...#O....#.##....OO#.O..O
            #...O.OO#O.O..O...#O.#..#.....O..OO...O..#...#.#.#..O......O..O.O.O.O...#..#O..#..#....#....#....O..""";

    public static void main(String[] args) {
//        StoneField stoneField = new StoneField(input);
        StoneField stoneField = new StoneField(test);
        stoneField.cycle();
        System.out.println(stoneField.getField());


//        List<StoneLine> lines = stoneField.getLines();
        int sum = 0;
//        for (StoneLine line : lines) {
//            Integer weight = line.getWeight();
//            System.out.println(weight);
//            sum += weight;
//        }


        System.out.println(sum);
    }

}