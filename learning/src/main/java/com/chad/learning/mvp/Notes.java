package com.chad.learning.mvp;

public class Notes {
    /**
     * MVP的好处：MVP模式将Activity中的业务逻辑全部分离出来，让Activity只做UI逻辑的处理，
     *           所有跟Android API无关的逻辑由Presenter层来完成。
     *
     * MVP理论知识：MVP和MVC类似分为三层
     *             Activity和Fragment视为View层，负责处理UI；
     *             Presenter层为业务处理层，既能调用UI逻辑又能请求数据，该层为纯Java层，不涉及任何Android API；
     *             Model层中包含着具体的数据请求，数据源。
     *
     * MVP之间的调用顺序为View -> Presenter -> Model，为了调用安全着想，不能反向调用，不可跨级调用
     *
     * http://www.jcodecraeer.com/a/anzhuokaifa/2017/1020/8625.html?1508484926
     */
}
