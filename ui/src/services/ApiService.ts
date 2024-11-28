import ApiParams from "./ApiParams";

export default class ApiService {
    public async request(params: ApiParams) {
        var formdata = new FormData();
        Object.entries(params.getBody()).forEach((entry) => {
            formdata.append(entry[0], entry[1]);
        })

        var headers = new Headers();
        Object.entries(params.getHeaders()).forEach((entry) => {
            headers.append(entry[0], entry[1]);
        })
        headers.append('Access-Control-Allow-Origin', '*')

        var requestOptions = {
            method: params.getMethod(),
            body: params.getMethod() !== 'GET' ? formdata : null,
            headers
        };

        let response = await fetch(params.getBaseUrl() + params.getEndpoint(), requestOptions);
        let json = await response.json();
        
        return json;
    }
}