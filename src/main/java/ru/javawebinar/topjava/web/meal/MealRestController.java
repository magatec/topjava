package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public List<MealTo> getAll() {
        log.info("getAll {}", authUserId());
        return MealsUtil.getTos(service.getAll(authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(String startDate, String endDate, String startTime, String endTime) {
        log.info("getAllFiltered {}", authUserId());
        return MealsUtil.getTos(service.getAllFiltered(authUserId(), startDate, endDate, startTime, endTime),
                SecurityUtil.authUserCaloriesPerDay());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id {}", meal, id);
        ValidationUtil.assureIdConsistent(meal, id);
        service.update(meal, id);
    }
}