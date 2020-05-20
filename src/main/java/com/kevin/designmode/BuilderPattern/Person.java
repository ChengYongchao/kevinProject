
package com.kevin.designmode.BuilderPattern;

/**
 * 在这里加入功能说明
 *
 * @author chengyongchao
 * @version 1.0, 2019年12月18日
 */
public class Person
{

    private String eyes;

    private String nose;

    private String mouth;

    private String ears;

    private String hair;

    /**
     * @param builder
     */
    public Person(Builder builder)
    {
        this.eyes = builder.eyes;
        this.nose = builder.nose;
        this.mouth = builder.mouth;
        this.ears = builder.ears;
        this.hair = builder.hair;
    }

    @Override
    public String toString()
    {
        return "Person [eyes=" + eyes + ", nose=" + nose + ", mouth=" + mouth + ", ears=" + ears + ", hair=" + hair
                + "]";
    }

    public static final class Builder
    {
        private String eyes;

        private String nose;

        private String mouth;

        private String ears;

        private String hair;

        public Builder()
        {}

        public Builder eyes(String eyes)
        {
            this.eyes = eyes;
            return this;
        }

        public Builder nose(String nose)
        {
            this.nose = nose;
            return this;
        }

        public Builder mouth(String mouth)
        {
            this.mouth = mouth;
            return this;
        }

        public Builder ears(String ears)
        {
            this.ears = ears;
            return this;
        }

        public Builder hair(String hair)
        {
            this.hair = hair;
            return this;
        }

        public Person build()
        {
            return new Person(this);
        }

    }
}
