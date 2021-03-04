package kg.rinat.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.rinat.dto.common.OperationResultResponse;
import kg.rinat.dto.counter.CounterRequestDto;
import kg.rinat.dto.counter.CounterResponseDto;
import kg.rinat.service.abstraction.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/counter")
@Api(value = "Счётчик")
public class CounterRestController {

    private final CounterService counterService;

    @Autowired
    public CounterRestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping
    @ApiOperation(value = "Создать счетчик с уникальным именем")
    public OperationResultResponse<String> createCounter(@RequestBody CounterRequestDto counterRequestDto) {
        return counterService.createCounter(counterRequestDto);
    }

    @PutMapping
    @ApiOperation(value = "Инкремент значения счетчика с указанным именем")
    public CounterResponseDto incrementCounter(@RequestBody CounterRequestDto counterRequestDto) {
        return counterService.incrementCounter(counterRequestDto);
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "Получить значения счетчика с указанным именем")
    public CounterResponseDto getCounterValue(@PathVariable String name) {
        return counterService.getCounterValue(name);
    }

    @DeleteMapping("/{name}")
    @ApiOperation(value = "Удалить счетчик с указанным именем")
    public OperationResultResponse<String> deleteCounterByName(@PathVariable String name) {
        return counterService.deleteCounterByName(name);
    }

    @GetMapping("/all/sum")
    @ApiOperation(value = "Получить суммарное значение всех счетчиков")
    public CounterResponseDto getSumOfAllCounterValues() {
        return counterService.getSumOfAllCounterValues();
    }

    @GetMapping("/all/names")
    @ApiOperation(value = "Получить уникальные имена счетчиков в виде списка")
    public OperationResultResponse<List<String>> getCounterNamesList() {
        return counterService.getCounterNamesList();
    }
}
