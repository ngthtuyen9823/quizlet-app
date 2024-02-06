package com.flashcard;

import com.flashcard.entity.Card;
import com.flashcard.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Test
    private void saveCardTest() throws Exception {


    }
}
