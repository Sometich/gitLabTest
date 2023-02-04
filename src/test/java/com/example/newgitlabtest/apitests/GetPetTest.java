package com.example.newgitlabtest.apitests;

import com.example.newgitlabtest.configure.TestsConfigure;
import com.example.newgitlabtest.pojo.Category;
import com.example.newgitlabtest.pojo.Pet;
import com.example.newgitlabtest.pojo.TagsItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public final class GetPetTest {
    private static final PetApi CONFIGURE = TestsConfigure.feignSettings();
    private long deleteID;
    private Pet pet;

    @AfterEach
    public void deletePet() {
        CONFIGURE.deletePet(deleteID);
    }

    @BeforeEach
    public void resetPet() {
        pet = new Pet(List.of("string"),
                "alex",
                0,
                new Category("string", 1),
                List.of(new TagsItem("string", 0)),
                "available");
    }

    /**
     * Проверка получения 200 кода при отправке существующего ID в базе.
     */
    @Test
    public void checkCode200() {
        long param = CONFIGURE.addPet(pet).getId();
        deleteID = param;
        Assertions.assertEquals(TestsConfigure.OK, CONFIGURE.getStatusCodePet(param).status(),
                "Статус не равен 200");
    }

    /**
     * Проверка получения по существующему ID данных с действительным значением поля name.
     */
    @Test
    public void checkValidName() {
        long param = CONFIGURE.addPet(pet).getId();
        Pet localPet = TestsConfigure.feignSettings().getPet(param);
        Assertions.assertEquals(pet.getName(), localPet.getName(),
                "The names are not the same, and therefore the service returned the wrong data");
        deleteID = localPet.getId();
    }
}
