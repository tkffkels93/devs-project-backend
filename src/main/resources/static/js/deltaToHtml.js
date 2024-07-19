const { QuillDeltaToHtmlConverter } = require('quill-delta-to-html');

function convert(deltaJson) {
    const delta = JSON.parse(deltaJson);
    const deltaOps = delta.ops ? delta.ops : delta; // `ops`가 없으면 최상위 객체를 사용
    const converter = new QuillDeltaToHtmlConverter(deltaOps, {});
    return converter.convert();
}

module.exports = { convert };