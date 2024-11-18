export default class ApiParams {
    private headers: Record<string, string> = {}
    private body: Record<string, string> = {}

    constructor(
        private readonly baseUrl: string,
        private readonly endpoint: string,
        private readonly method: 'GET' | 'POST' | 'PUT' | 'DELETE'
    ) {}

    public getBaseUrl() {
        return this.baseUrl;
    }

    public setHeader(name: string, value: string) {
        this.headers[name] = value;
    }

    public getHeader(name: string) {
        return this.headers[name];
    }

    public getHeaders() {
        return this.headers;
    }

    public getMethod() {
        return this.method;
    }

    public getEndpoint() {
        return this.endpoint;
    }

    public addBodyField(name: string, value: string) {
        this.body[name] = value;
    }

    public getBody() {
        return this.body;
    }
}