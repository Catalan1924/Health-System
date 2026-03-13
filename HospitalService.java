public abstract class HospitalService {
    private String serviceName;

    public HospitalService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        if (serviceName == null || serviceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Service name cannot be empty.");
        }
        this.serviceName = serviceName;
    }

    public abstract void executeService();
}