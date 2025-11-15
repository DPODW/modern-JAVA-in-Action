import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

@Slf4j
public class ModernJavaTest2 {

    private List<TestAppleDto> testAppleDtoList;

    @BeforeEach
    public void setUpTestData() {
        testAppleDtoList = new ArrayList<>();

        TestAppleDto testAppleDto1 = new TestAppleDto("GreenApple",GREEN,45);
        TestAppleDto testAppleDto2 = new TestAppleDto("RedApple",RED,55);
        testAppleDtoList.add(testAppleDto1);
        testAppleDtoList.add(testAppleDto2);
    }

    @FunctionalInterface //함수형 인터페이스 어노테이션 ( 추상 메소드가 1개 이상일시 에러 )
    public interface LamdaInterface {
        int apply(int first, int second);
    }


    @DisplayName("lamda test")
    @Test
    public void lamdaTest() {

        /**
         * 람다식을 사용하려면 함수형 인터페이스 (구현해야 할 추상 메소드가 하나만 정의된 인터페이스) 가 필요하다.
         * 해당 인터페이스를 구현하는 방식으로 사용한다.
         * 아래는 동작 과정 확인을 위한 로직이고, 람다의 장점이 하나도 보여지지 않았다.
         * 람다는 [ 조건 ] 자체를 사용할 수 있기 때문에, 조건별 처리가 많이 필요한 상황에서 유용하게 쓰일 수 있다.
         * ㄴ> 엄밀히 말하면 [ 코드 자체 ] 를 넘길 수 있다.
         **/

        LamdaInterface lamda1 = (first,second) -> first+second;
        int result = lamda1.apply(1, 2);

        log.info("result: {}", result);
    }



    @Test
    public void methodLamda(){
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort(String::compareToIgnoreCase);

        log.info("str: {}", str);
    }


}
