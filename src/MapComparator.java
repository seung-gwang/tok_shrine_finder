import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MapComparator {
    public static void getUnfoundShrinePositions() throws Exception {
        BufferedImage baseUp = ImageIO.read(new File("C:/Users/User/IdeaProjects/shrineFinder/src/baseUp.jpeg"));
        BufferedImage baseDown = ImageIO.read(new File("C:/Users/User/IdeaProjects/shrineFinder/src/baseDown.jpeg"));
        BufferedImage userUp = ImageIO.read(new File("C:/Users/User/IdeaProjects/shrineFinder/src/userUp.jpeg"));
        BufferedImage userDown = ImageIO.read(new File("C:/Users/User/IdeaProjects/shrineFinder/src/userDown.jpeg"));


        Set<Coordinate> baseCoords = new HashSet<>();
        baseCoords.add(new Coordinate(506,119));
        baseCoords.add(new Coordinate(533,262));
        baseCoords.add(new Coordinate(303,288));
        baseCoords.add(new Coordinate(404,345));
        baseCoords.add(new Coordinate(567,529));
        baseCoords.add(new Coordinate(670,257));

        //Upper map
        int baseUpWidth = baseUp.getWidth();
        int userUpWidth = userUp.getWidth();

        int baseUpHeight = baseUp.getHeight();
        int userUpHeight = userUp.getHeight();

        //이미지 크기 동일한지 확인 : 1280 X 720
        if(baseUpWidth == userUpWidth && baseUpHeight == userUpHeight) {
            System.out.println("SIZE CHECK OK");
        }

        //user image의 유효성 확인 :
        for(Coordinate c : baseCoords) {
            int blueCnt = 0;
            int x = c.getX();
            int y = c.getY();

            //x는 증가, y는 감소
            for(int i = 0; i < 7; ++i) {
                int cx = x + i;
                int cy = y - i;

                //int baseUpPixel = baseUp.getRGB(x, y);
                int userUpPixel = userUp.getRGB(x,y);
                Color color = new Color(userUpPixel, true);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                if((b > g && b > r) ||  b > 200) {
                    blueCnt++;
                }
            }

            if(blueCnt >= 6) { //파란 지점이 7개 중에 6개 이상이라면 '사당'점이라고 판단함.
                System.out.printf("(%d, %d) -> 이곳의 사당을 찾았습니다!!.\n", x, y);
            }
            else {
                System.out.printf("(%d, %d) -> 여기에 사당이 있습니다. 찾아가세요.\n", x, y);
            }

        }


//        for(int y = 0; y < baseUpHeight; ++y) {
//            for(int x = 0; x < baseUpWidth; ++x) {
//                int baseUpPixel = baseUp.getRGB(x, y);
//                Color color = new Color(baseUpPixel, true);
//
//                int r = color.getRed();
//                int g = color.getGreen();
//                int b = color.getBlue();
//
//                System.out.printf("%d %d %d\n", r, g, b);
//            }
//        }
    }
}
