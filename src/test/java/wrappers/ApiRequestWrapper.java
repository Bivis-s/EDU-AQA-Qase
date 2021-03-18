package wrappers;

import api.objects.ApiRequest;
import lombok.Data;

@Data
public class ApiRequestWrapper<T extends ApiRequest> {
    T apiRequest;
}
