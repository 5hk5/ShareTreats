# ShareTreats 사전과제 - 상품 교환 서비스

## 실행 환경

언어 : Java (jdk-11.0.15)

IDE : IntelliJ

## 테스트 케이스


### ✔PASS

HELP

help

hElP


CHECK 000 000 001

check 000 000 001

chECK 000 000 001


CLAIM dEjgDx 000 000 001

claim dEjgDx 000 000 001

ClaiM dEjgDx 000 000 001
 
 
### ✖FAIL

#### 1. 오타
he ➡ '안내된 형식에 맞춰 다시 입력해주세요. HELP를 입력하시면 안내사항을 다시 확인하실 수 있습니다.'


#### 2. 잘못된 형식

- 상품 코드 누락

check ➡ '상품 코드 9자리를 입력해 주세요.'

- 상점 코드, 상품 코드 누락

claim ➡ '상점 코드와 상품 코드를 입력해 주세요.'

- 상점 코드 누락

claim 000 000 001 ➡ '상점 코드 6자리를 입력해 주세요.'

- 불필요한 상점 코드 입력

check aabbbb 000 000 001 ➡ '상품 코드만 입력해 주세요.'

- 코드 자릿수 불일치

check 0000 ➡ '상품코드 9자리를 입력해 주세요.'

claim aa 000 000 001 ➡ '상점 코드 6자리를 입력해 주세요.'

claim aaaaaa 000 bb ➡ '상품 코드 9자리를 입력해 주세요.'      
  
#### 3. 상점/상품 코드 오기입

- 상품 코드 오기입

check 000 000 000 ➡ '입력하신 상품코드가 존재하지 않습니다.'

claim dEjgDx 000 000 000 ➡ '입력하신 상품코드가 존재하지 않습니다.'

- 상점 코드 오기입

claim aaaaaa 000 000 001 ➡ '입력하신 상점코드가 존재하지 않습니다.'


## 고민

### 1. 상품 (상품코드, 상점코드) 세팅

실무에서는 어드민 도메인에서 상품을 세팅할 때 저장된 상점 코드들 중 교환 허가할 상점 코드들을 선택하고, 상품코드를 자동 증가값으로 생성해 Product 객체 생성, DB 저장하는 것이 일반적일 것이라고 생각했습니다.

저는 사전 과제에서 고객이 사용하는 비즈니스 로직에 집중하고 유효성 검사 위주로 진행하고자 하여 클래스에서 직접 상품을 세팅하고 테스트를 실행했습니다.

ex) 상점 코드 : dEjgDx, NoejZW  상품 코드 : 000000001 ~ 000000010


### 2. 상점과 상품 간의 관계

하나의 상품코드가 하나의 상점코드와 매칭되는 것과, 하나의 상품코드가 여러 상점코드들과 매칭되는 것 중에 후자가 더 현실적이라고 판단했습니다.

예를 들어 특정 초콜릿 상품을 AA편의점, BB편의점 등 여러 상점에서 교환할 수 있도록 하는 것이 합리적이라고 생각했습니다.

따라서 하나의 상품코드당 여러 상점 코드를 갖게 하고 (하나의 상품코드가 여러 상점에서 공유됨) 교환 시 교환 실행된 상점코드를 따로 저장하는 코드를 작성했습니다.

### 3. 유효성 검사의 정도

입력값의 유효성에 대해 상세하게 안내하는 것이 사용자 입장에서 좋지만 if문이 많아져 전체 성능에 영향끼칠 가능성이 있다고 생각했습니다.

이에 따라 HELP, CHECK, CLAIM 와 같은 입력값을 구분하는 if문은 switch문으로 변경했고, 내부 if문에서 유효성 검사 메시지를 더 구체적으로 표시했습니다.
