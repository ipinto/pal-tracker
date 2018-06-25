package io.pivotal.pal.tracker;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long, TimeEntry> repository = new ConcurrentHashMap<>();

    @Override
    public TimeEntry create(TimeEntry entry) {
        entry.setId(generateId());
        repository.put(entry.getId(), entry);
        return entry;
    }

    @Override
    public TimeEntry find(long id) {
        return repository.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry entity) {
        entity.setId(id);
        repository.put(id, entity);
        return entity;
    }

    @Override
    public TimeEntry delete(long id) {
        return repository.remove(id);
    }

    private long generateId() {
        return repository.keySet().stream()
                .mapToLong(l -> l)
                .max()
                .orElse(0L) + 1L;
    }
}
