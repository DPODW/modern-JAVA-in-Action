import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

@Slf4j
public class ModernJavaTest1 {
// 메소드 참조 , 람다로 코드 개선해보기 ( 1 )

    private List<TestAppleDto> testAppleDtoList;

    @BeforeEach
    public void setUpTestData() {
        testAppleDtoList = new ArrayList<>();

        TestAppleDto testAppleDto1 = new TestAppleDto("GreenApple",GREEN,45);
        TestAppleDto testAppleDto2 = new TestAppleDto("RedApple",RED,200);
        testAppleDtoList.add(testAppleDto1);
        testAppleDtoList.add(testAppleDto2);
    }


    @Test
    @DisplayName("before Legacy test 1")
    public void legacyTest1() {
        List<TestAppleDto> appleDtoListResult = before1FilterGreenApples(testAppleDtoList);
        log.info("appleDtoListResult : {}",appleDtoListResult);
    }

    public static List<TestAppleDto> before1FilterGreenApples(List<TestAppleDto> inventory) {
        List<TestAppleDto> result = new ArrayList<>();
        for (TestAppleDto apple : inventory) {
            if(GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<TestAppleDto> before1FilterHeavyApples(List<TestAppleDto> inventory) {
        List<TestAppleDto> result = new ArrayList<>();
        for (TestAppleDto apple : inventory) {
            if(apple.getWeight()>150) {
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    @DisplayName("before Legacy test 2")
    public void legacyTest2() {
        List<TestAppleDto> appleDtoListResult = before2FilterApples(testAppleDtoList, ModernJavaTest1::before2IsGreenApple);
       /**
        *  [대상] :: [메서드 이름] : 메소드 레퍼런스 기능. 간단하게 메소드에 직접 접근할 수 있게 해준다.
        *  ModernJavaTest::before2IsGreenApple : 현재 before2IsGreenApple 는 static 이기 때문에 특정 객체에 속하지 않고 클래스 자체에 속한다.
        *  그렇기 때문에 대상을 클래스 자체로 잡아주어야 한다.
        *
        *  검증할 배열과 (testAppleDtoList), 해당 배열을 검증할 조건 (before2IsGreenApple) 을 넘긴다
        **/

        List<TestAppleDto> appleDtoListLamdaResult1 = before2FilterApples(testAppleDtoList, (TestAppleDto appleDto) -> GREEN.equals(appleDto.getColor()));
        List<TestAppleDto> appleDtoListLamdaResult2 = before2FilterApples(testAppleDtoList, (TestAppleDto appleDto) -> appleDto.getWeight() > 150);

        log.info("appleDtoListLamdaResult2 : {}",appleDtoListLamdaResult2);
        /**
        * before2IsGreenApple 해당 조건 메소드가 1회만 사용된다면 (사용빈도가 적다면), 굳이 정의해서 사용할 필요가 없다.
        * 람다식을 활용하여, 즉석으로 조건을 만들어서 Predicate 로 넘기는것도 가능하다.
        **/
    }

    public List<TestAppleDto> before2FilterApples(List<TestAppleDto> apples,Predicate<TestAppleDto> predicate) {
        List<TestAppleDto> result = new ArrayList<>();

        for (TestAppleDto apple : apples) {
            if(predicate.test(apple)) {
                //predicate 가 받은 조건식에 apple 을 넣고 필터링한다.
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean before2IsGreenApple(TestAppleDto apple) {
        return GREEN.equals(apple.getColor());
    }

    public static boolean before2IsHeavyApple(TestAppleDto apple) {
        return apple.getWeight()>150;
    }

    public interface Predicate<T>{
        /**
         * Predicate<T> : 받은 인자를 판단하여 TRUE/FALSE 를 반환하는 인터페이스
         * 조건 자체를 전달하여, 해당 조건의 참 거짓을 수행.
         **/
        boolean test(T t);
    }


}
