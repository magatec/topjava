package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

//    {
//        MealsUtil.meals.forEach(this::save);
//    }

    @Override
    public Meal save(Meal meal, int userId) {
        repository.put(userId, new ConcurrentHashMap<>());
        Map<Integer, Meal> userMeals = getUserMeals(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id, userId) == null) {
            return false;
        }
        return getUserMeals(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(userId).get(id);
    }

    public Map<Integer, Meal> getUserMeals(int userId) {
        return repository.get(userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getUserMeals(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllFiltered(int userId, LocalDate startData, LocalDate endData, LocalTime startTime, LocalTime endTime) {
        return getUserMeals(userId).values().stream()
                .filter(meal -> startData.compareTo(meal.getDate()) <= 0 && endData.compareTo(meal.getDate()) >= 0)
                .filter(meal -> startTime.compareTo(meal.getTime()) <= 0 && endTime.compareTo(meal.getTime()) >= 0)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

