package example;



class Computer {
    private String processor;
    private int ram;

    // private constructor ensures only Builder can instantiate it
    private Computer(Builder builder) {
        this.processor = builder.processor;
        this.ram = builder.ram;
    }

    public String getProcessor() {
        return this.processor;
    }

    public int getRam() {
        return this.ram;
    }

    // static nested class
    public static class Builder {
        private String processor;
        private int ram;

        public Builder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Computer build() {
            // can access private constructor of the outer class
            return new Computer(this);
        }
    }
}

public class ComputerDemo {
    public static void main(String[] args) {
        Computer comp = new Computer.Builder().setProcessor("M3 Max").setRam(32).build();
        System.out.println(comp.getProcessor());
    }
}
