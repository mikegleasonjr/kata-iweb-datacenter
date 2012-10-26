package utils;
import datacenter.Vm;

public class VmBuilder {
    private int size;

    public static Vm a(VmBuilder builder) {
        return builder.build();
    }

    public static VmBuilder aVm() {
        return new VmBuilder();
    }

    public Vm build() {
        return new Vm(size);
    }

    public VmBuilder withSize(int size) {
        this.size = size;
        return this;
    }
}
