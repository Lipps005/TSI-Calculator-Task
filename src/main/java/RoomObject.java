import org.junit.jupiter.api.Test;

import java.awt.*;


public class RoomObject
{
    private float Height;
    private float Width;
    private float excludableArea = 0.0f;//protected so it can be accessed in tests (maybe not needed)
    private float objectArea = 0.0f;

    private RoomObject(Builder builder)
    {
        this.Height = builder.Height;
        this.Width = builder.Width;
        this.excludableArea = builder.excludableArea;
        this.objectArea = builder.objectArea;
    }

    public static class Builder
    {
        private float Height;
        private float Width;

        protected float excludableArea;
        public float objectArea;

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

            this.excludableArea += width*height;
            return this;
        }

        public Builder setSize(float width, float height)
        {
            this.objectArea = width*height;
            return this;
        }
        public RoomObject build(){
            return new RoomObject(this);
        };

    }

    protected float getPaintableSize()
    {
        //if a user adds more excludable areas than the size of the wall,
        //return 0

        return this.objectArea < this.excludableArea ? 0 : this.objectArea - this.excludableArea;
    };

}
