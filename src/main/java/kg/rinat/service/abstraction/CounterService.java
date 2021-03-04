package kg.rinat.service.abstraction;

import kg.rinat.dto.common.OperationResultResponse;
import kg.rinat.dto.counter.CounterRequestDto;
import kg.rinat.dto.counter.CounterResponseDto;

import java.util.List;

public interface CounterService {
    OperationResultResponse<String> createCounter(CounterRequestDto counterRequestDto);

    CounterResponseDto incrementCounter(CounterRequestDto counterRequestDto);

    CounterResponseDto getCounterValue(String name);

    OperationResultResponse<String> deleteCounterByName(String name);

    CounterResponseDto getSumOfAllCounterValues();

    OperationResultResponse<List<String>> getCounterNamesList();
}
