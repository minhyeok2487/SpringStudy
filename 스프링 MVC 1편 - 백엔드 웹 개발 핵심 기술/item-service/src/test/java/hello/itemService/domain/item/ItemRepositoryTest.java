package hello.itemService.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("ItemA", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }


    @Test
    void findAll() {
        //given
        Item itemA = new Item("ItemA", 10000, 11);
        Item itemB = new Item("ItemB", 20000, 12);
        Item itemC = new Item("ItemC", 30000, 13);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(itemA, itemB, itemC);
    }

    @Test
    void update() {
        //given
        Item item = new Item("ItemA", 10000, 10);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        //when
        Item updateParam = new Item("ItemB", 12340, 13);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}