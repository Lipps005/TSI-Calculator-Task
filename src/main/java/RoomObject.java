import java.awt.*;


public class RoomObject
{
    private float Height;
    private float Width;
    protected float size = 0.0f;

    private RoomObject(Builder builder)
    {
        this.Height = builder.Height;
        this.Width = builder.Width;
        this.size = builder.size;
    }

    public static class Builder
    {
        private float Height;
        private float Width;
        private float size;

        public Builder setHeight(float height)
        {
            this.Height = height;
            return this;
        }
        public Builder setWidth(float width)
        {
            this.Width = width;
            return this;
        }

        public Builder addSocket(float width, float height)
        {
            this.size -= width*height;
            return this;
        }

        public Builder addDoor(float width, float height)
        {
            this.size -= width*height;
            return this;
        }
        public Builder setSize(float width, float height)
        {
            this.size = width*height;
            return this;
        }
        public RoomObject build(){
            return new RoomObject(this);
        };

    }

    protected float getPaintableSize()
    {
        return size;
    };

}
