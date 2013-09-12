package org.solovyev.problems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class IntegerKnapsackProblem {

	@Nonnull
	public static Items solve(int capacity, @Nonnull Collection<Item> items) {
		final Cache cache = new Cache(capacity);
		max(capacity, items, cache);
		return cache.getItems(capacity);
	}

	private static int max(int capacity, @Nonnull Collection<Item> items, @Nonnull Cache cache) {
		if (capacity > 0) {
			final Items fromCache = cache.getItems(capacity);
			if (fromCache == null) {
				final int previousValue = max(capacity - 1, items, cache);

				Items currentResultItems = null;
				for (Item item : items) {
					if (capacity - item.size >= 0) {
						final int itemMax = max(capacity - item.size, items, cache) + item.value;
						final Items resultItems = cache.getItems(capacity - item.size);
						if(currentResultItems == null || itemMax > currentResultItems.value) {
							currentResultItems = new Items(itemMax, resultItems == null ? Collections.<Item>emptyList() : resultItems.items, item);
						}
					}
				}

				if(previousValue >= currentResultItems.value) {
					Items previousResultItems = cache.getItems(capacity - 1);
					if (previousResultItems != null) {
						cache.setItems(capacity, previousResultItems);
						return previousResultItems.value;
					} else {
						cache.setItems(capacity, new Items(0, Collections.<Item>emptyList(), null));
						return 0;
					}
				} else {
					cache.setItems(capacity, currentResultItems);
					return currentResultItems.value;
				}
			} else {
				return fromCache.value;
			}
		} else {
			return 0;
		}
	}

	private static final class Cache {

		private final Items[] items;

		private Cache(int capacity) {
			items = new Items[capacity];
		}

		private void setItems(int capacity, @Nonnull Items item) {
			items[capacity-1] = item;
		}

		@Nullable
		private Items getItems(int capacity) {
			if (capacity-1 >= 0) {
				return items[capacity-1];
			} else {
				return null;
			}
		}
	}

	public static final class Items {

		@Nonnull
		private final List<Item> items = new ArrayList<Item>();

		private final int value;

		private Items(int value, @Nonnull List<Item> items, @Nullable Item item) {
			this.value = value;
			this.items.addAll(items);
			if (item != null) {
				this.items.add(item);
			}
		}

		@Nonnull
		public List<Item> getItems() {
			return items;
		}

		public int getValue() {
			return value;
		}
	}

	public static final class Item {

		private final int size;
		private final int value;

		private Item(int size, int value) {
			this.size = size;
			this.value = value;
		}

		public static Item newItem(int size, int value) {
			return new Item(size, value);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Item item = (Item) o;

			if (size != item.size) return false;
			if (value != item.value) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = size;
			result = 31 * result + value;
			return result;
		}
	}
}
