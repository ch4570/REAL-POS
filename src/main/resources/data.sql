INSERT INTO CATEGORY(CATEGORY_NAME) VALUES('디저트');
INSERT INTO CATEGORY(CATEGORY_NAME) VALUES('커피');

-- 디저트 상품 추가
INSERT INTO PRODUCT (CATEGORY_ID, PRODUCT_COST, PRODUCT_PRICE, PRODUCT_NAME, PRODUCT_BARCODE, PRODUCT_DESCRIPTION, PRODUCT_EXPIRATION_DATE, PRODUCT_SIZE, REG_DATE, MOD_DATE)
VALUES
    (1, 5000, 7000, '딸기 생크림 케이크', '1234567890123', '부드러운 생크림과 신선한 딸기가 듬뿍 올라간 케이크입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 5500, 7500, '초코 우유 파이', '2345678901234', '진한 초코와 부드러운 우유 크림이 들어간 파이입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 4800, 6800, '블루베리 치즈케이크', '3456789012345', '진한 치즈와 상큼한 블루베리가 어우러진 케이크입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 5200, 7200, '마카롱 세트', '4567890123456', '다양한 맛의 마카롱이 들어간 세트 상품입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 4500, 6500, '생과일 타르트', '5678901234567', '신선한 과일이 가득한 타르트입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 6000, 8000, '망고 파르페', '6789012345678', '달콤한 망고와 시원한 아이스크림이 들어간 파르페입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- 커피 상품 추가
INSERT INTO PRODUCT (CATEGORY_ID, PRODUCT_COST, PRODUCT_PRICE, PRODUCT_NAME, PRODUCT_BARCODE, PRODUCT_DESCRIPTION, PRODUCT_EXPIRATION_DATE, PRODUCT_SIZE, REG_DATE, MOD_DATE)
VALUES
    (2, 3000, 4500, '아메리카노', '1234567890123', '진한 에스프레소에 물을 타서 만든 커피입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 3500, 5000, '카페 라떼', '2345678901234', '에스프레소와 스팀밀크를 함께 우려낸 커피 음료입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- 이하 8개의 커피 상품 추가
    (2, 3200, 4700, '카푸치노', '3456789012345', '에스프레소 위에 부드러운 우유 거품을 얹은 커피 음료입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2800, 4300, '에스프레소', '4567890123456', '진한 커피의 향과 맛을 느낄 수 있는 강렬한 에스프레소입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- 이하 6개의 커피 상품 추가
    (2, 3300, 4900, '바닐라 라떼', '5678901234567', '달콤한 바닐라 시럽이 들어간 커피 음료입니다.', CURRENT_TIMESTAMP, 'Large', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 3100, 4600, '모카 프라푸치노', '6789012345678', '초콜릿과 커피가 어우러진 시원한 음료입니다.', CURRENT_TIMESTAMP, 'Small', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- 검색 키워드 추가
INSERT INTO PRODUCT_SEARCH (PRODUCT_SEARCH_KEYWORD, PRODUCT_ID)
VALUES
    ('ㄸㄱ ㅅㅋㄹ ㅋㅇㅋ', 1),
    ('ㅊㅋ ㅇㅇ ㅍㅇ', 2),
    ('ㅂㄹㅂㄹ ㅊㅈㅋㅇㅋ', 3),
    ('ㅁㅋㄹ ㅅㅌ', 4),
    ('ㅅㄱㅇ ㅌㄹㅌ', 5),
    ('ㅁㄱ ㅍㄹㅍ', 6),
    ('ㅇㅁㄹㅋㄴ', 7),
    ('ㅋㅍ ㄹㄸ', 8),
    ('ㅋㅍㅊㄴ', 9),
    ('ㅇㅅㅍㄹㅅ', 10),
    ('ㅂㄴㄹ ㄹㄸ', 11),
    ('ㅁㅋ ㅍㄹㅍㅊㄴ', 12)