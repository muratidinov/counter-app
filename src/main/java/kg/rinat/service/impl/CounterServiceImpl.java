package kg.rinat.service.impl;

import io.swagger.annotations.Api;
import kg.rinat.dto.common.OperationResultResponse;
import kg.rinat.dto.counter.CounterRequestDto;
import kg.rinat.dto.counter.CounterResponseDto;
import kg.rinat.exception.CounterException;
import kg.rinat.model.Counter;
import kg.rinat.service.abstraction.CounterService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CounterServiceImpl implements CounterService {

    public final Map<String, Counter> counterMap;

    public CounterServiceImpl() {
        counterMap = new HashMap<>();
    }

    @Override
    public OperationResultResponse<String> createCounter(CounterRequestDto counterRequestDto) {
        String name = counterRequestDto.getName();
        if (counterMap.containsKey(name)) {
            throw new CounterException("Счётчик с именем " + name + " уже существует");
        }
        counterMap.put(name, new Counter(name, new AtomicInteger(0)));
        String message = "Счётчик с именем " + name + " успешно создан";
        log.info(message);
        return new OperationResultResponse<>(message);
    }

    @Override
    public CounterResponseDto incrementCounter(CounterRequestDto counterRequestDto) {
        String name = counterRequestDto.getName();
        Counter counter = getCounterIfExists(name);
        int lastValue = counter.getCounter().incrementAndGet();
        String message = "Счётчик с именем " + name + " инкрементирован. Текущее значение: " + lastValue;
        log.info(message);
        return new CounterResponseDto(message);
    }

    @Override
    public CounterResponseDto getCounterValue(String name) {
        Counter counter = getCounterIfExists(name);
        log.info("Запрос на получение значения счётчика с именем: " + name);
        return new CounterResponseDto(String.valueOf(counter.getCounter().get()));
    }

    @Override
    public OperationResultResponse<String> deleteCounterByName(String name) {
        getCounterIfExists(name);
        counterMap.remove(name);
        String message = "Счётчик с именем " + name + " успешно удалён";
        log.info(message);
        return new OperationResultResponse<>(message);
    }

    @Override
    public CounterResponseDto getSumOfAllCounterValues() {
        long sum = 0;
        if (!counterMap.isEmpty()) {
            sum = counterMap.values().stream().mapToInt(counter -> counter.getCounter().get()).sum();
        }
        log.info("Запрос на получение значения всех счётчиков: " + sum);
        return new CounterResponseDto(String.valueOf(sum));
    }

    @Override
    public OperationResultResponse<List<String>> getCounterNamesList() {
        OperationResultResponse<List<String>> operationResultResponse = new OperationResultResponse<>();
        if (!counterMap.isEmpty()) {
            List<String> namesList = counterMap.values().stream().map(Counter::getName).collect(Collectors.toList());
            operationResultResponse.setResponse(namesList);
        } else {
            operationResultResponse.setResponse(new ArrayList<>());
        }
        log.info("Запрос на получение имён всех счётчиков");
        return operationResultResponse;
    }

    private Counter getCounterIfExists(String name) {
        if (!counterMap.containsKey(name)) {
            throw new CounterException("Счётчик с именем " + name + " не существует");
        }
        return counterMap.get(name);
    }
}
