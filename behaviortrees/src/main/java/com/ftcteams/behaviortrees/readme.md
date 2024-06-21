Recommend making a class like LeafNode that all Actions and Conditions extend from
so that you don't have to keep typecasting the parameter.

```
abstract public class LeafNode extends Node{
    @Override
    final public State tick(DebugTree debug, Object obj) {
        return tick(debug, (Robot)obj);
    }
    public abstract State tick(DebugTree debug, Robot board);
}
```