package com.vlasquez.poweradapter.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.vlasquez.poweradapter.item.ItemRenderer;
import com.vlasquez.poweradapter.item.RecyclerItem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecyclerDataSourceTest {

  private final ItemRenderer<? extends RecyclerItem> rendererOne = new TestRenderer(1);
  private final ItemRenderer<? extends RecyclerItem> rendererTwo = new TestRenderer(2);

  private final TestItem itemOne = new TestItem(1, "r1");
  private final TestItem itemTwo = new TestItem(2, "r2");
  private final TestItem itemThree = new TestItem(1, "r3");

  private RecyclerDataSource dataSource;

  @Before
  public void setUp() throws Exception {

    List<RecyclerItem> itemList = Arrays.<RecyclerItem>asList(itemOne, itemTwo, itemThree);
    Map<String, ItemRenderer<? extends RecyclerItem>> renderers = new HashMap<>();

    renderers.put("r1", rendererOne);
    renderers.put("r2", rendererTwo);

    dataSource = new RecyclerDataSource(renderers);
    dataSource.seedData(itemList);
  }

  @Test
  public void rendererForType() {
    assertEquals(rendererOne, dataSource.rendererForType(rendererOne.layoutRes()));
    assertEquals(rendererTwo, dataSource.rendererForType(rendererTwo.layoutRes()));
  }

  @Test
  public void viewResourceForPosition() {
    assertEquals(rendererOne.layoutRes(), 1);
    assertEquals(rendererTwo.layoutRes(), 2);
  }

  @Test
  public void getCount() {
    assertEquals(3,dataSource.getCount());
  }

  @Test
  public void getItem() {
    assertEquals(itemOne, dataSource.getItem(0));
    assertEquals(itemThree, dataSource.getItem(2));
  }

  static class TestItem implements RecyclerItem {
    private final long id;

    private final String key;

    public TestItem(long id, String key) {
      this.id = id;
      this.key = key;
    }

    @Override public long getId() {
      return 0;
    }

    @Override public String renderKey() {
      return null;
    }
  }

  static class TestRenderer implements ItemRenderer<RecyclerItem> {

    private final int layoutRes;

    public TestRenderer(int layoutRes) {
      this.layoutRes = layoutRes;
    }

    @Override public int layoutRes() {
      return layoutRes;
    }

    @Override public View createView(@NonNull ViewGroup parent) {
      return null;
    }

    @Override public void render(@NonNull View itemView, @NonNull RecyclerItem item) {

    }
  }
}